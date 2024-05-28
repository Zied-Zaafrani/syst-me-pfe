package com.ziedzaafrani.project.appel_offre;

import com.ziedzaafrani.project.structure.Structure;
import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ziedzaafrani.project.common.PageResponse;
import com.ziedzaafrani.project.projet.Projet;
import com.ziedzaafrani.project.projet.ProjetRepository;
import com.ziedzaafrani.project.user.User;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;


import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class AppelOffreService {

    private static final Logger logger = LoggerFactory.getLogger(AppelOffreService.class);

    private final AppelOffreRepository appelOffreRepository;
    private final AppelOffreMapper appelOffreMapper;
    private final ProjetRepository projetRepository;

    public Integer save(AppelOffreRequest request, Authentication connectedUser) {
        logger.info("Received request to save an AppelOffre. Title: {}", request.titreRequestDto());

        // Retrieve current user
        User user = (User) connectedUser.getPrincipal();
        logger.info("Current user: {}", user.getEmail());

        // Retrieve the user's structure
        Structure userStructure = user.getStructure();
        logger.info("User's structure: {}", userStructure.getTitre());

        // Retrieve the project by ID
        Projet projet = projetRepository.findById(request.projetIdRequestDto())
                .orElseThrow(() -> {
                    logger.error("No Project found with ID: {}", request.projetIdRequestDto());
                    return new EntityNotFoundException("No Project found with ID: " + request.projetIdRequestDto());
                });

        // Check if an AppelOffre with the same title exists in the same project
        if (appelOffreRepository.existsByTitreAndProjet(request.titreRequestDto(), projet)) {
            logger.warn("An AppelOffre with the same title already exists in this project: {}", projet.getTitre());
            throw new IllegalArgumentException("An AppelOffre with the same title already exists in this project");
        }

        // Map request to AppelOffre
        AppelOffre appelOffre = appelOffreMapper.toAppelOffre(request, projet);

        // Save the AppelOffre
        AppelOffre savedAppelOffre = appelOffreRepository.save(appelOffre);
        Integer appelOffreId = savedAppelOffre.getId();

        logger.info("AppelOffre saved successfully with ID: {}", appelOffreId);

        return appelOffreId;
    }


    public AppelOffreResponse findAppelOffreById(Integer appelOffreId) {
        logger.info("Received request to find an AppelOffre by ID: {}", appelOffreId);

        return appelOffreRepository.findById(appelOffreId)
                .map(appelOffreMapper::toAppelOffreResponse)
                .orElseThrow(() -> new EntityNotFoundException("No AppelOffre found with ID: " + appelOffreId));
    }

    public PageResponse<AppelOffreResponse> findAllAppelOffresByUserStructure(int page, int size, Authentication connectedUser) {
        logger.info("Received request to find all AppelOffres by user's structure. Page: {}, Size: {}", page, size);

        User user = (User) connectedUser.getPrincipal();
        logger.info("the current connected user. id: {}, structure: {}", user.getId(), user.getStructure());

        Structure userStructure = user.getStructure();

        if (userStructure == null) {
            throw new IllegalStateException("User is not associated with any structure.");
        }

        Pageable pageable = PageRequest.of(page, size, Sort.by("creationDate").descending());
        logger.info("checking find by structure {}, id : {}", userStructure, userStructure.getId());

        Page<AppelOffre> appelOffres = appelOffreRepository.findByStructure(userStructure, pageable);

        List<AppelOffreResponse> appelOffreResponses = appelOffres.getContent().stream()
                .map(appelOffreMapper::toAppelOffreResponse)
                .collect(Collectors.toList());

        logger.info("All AppelOffres retrieved successfully.");
        return new PageResponse<>(
                appelOffreResponses,
                appelOffres.getNumber(),
                appelOffres.getSize(),
                appelOffres.getTotalElements(),
                appelOffres.getTotalPages(),
                appelOffres.isFirst(),
                appelOffres.isLast()
        );
    }

    public PageResponse<AppelOffreResponse> findAllAppelOffresByProjetId(Integer projetId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("startDate").descending());

        Projet projet = projetRepository.findById(projetId)
                .orElseThrow(() -> {
                    logger.error("No Project found with ID: {}", projetId);
                    return new EntityNotFoundException("No Project found with ID: " + projetId);
                });

        Page<AppelOffre> appelOffresPage = appelOffreRepository.findByProjet(projet, pageable);

        return new PageResponse<>(
                appelOffresPage.getContent().stream()
                        .map(appelOffreMapper::toAppelOffreResponse)
                        .collect(Collectors.toList()),
                appelOffresPage.getNumber(),
                appelOffresPage.getSize(),
                appelOffresPage.getTotalElements(),
                appelOffresPage.getTotalPages(),
                appelOffresPage.isFirst(),
                appelOffresPage.isLast()
        );
    }

    public PageResponse<AppelOffreResponse> findAllAppelOffres(int page, int size) {
        logger.info("Received request to find all AppelOffres.");

        Pageable pageable = PageRequest.of(page, size, Sort.by("creationDate").descending());
        Page<AppelOffre> appelOffres = appelOffreRepository.findAll(pageable);

        List<AppelOffreResponse> appelOffreResponses = appelOffres.stream()
                .map(appelOffreMapper::toAppelOffreResponse)
                .collect(Collectors.toList());

        return new PageResponse<>(
                appelOffreResponses,
                appelOffres.getNumber(),
                appelOffres.getSize(),
                appelOffres.getTotalElements(),
                appelOffres.getTotalPages(),
                appelOffres.isFirst(),
                appelOffres.isLast()
        );
    }

    public void delete(Integer appelOffreId) {
        logger.info("Received request to delete AppelOffre with ID: {}", appelOffreId);

        AppelOffre appelOffre = appelOffreRepository.findById(appelOffreId)
                .orElseThrow(() -> new EntityNotFoundException("No AppelOffre found with ID: " + appelOffreId));

        appelOffreRepository.delete(appelOffre);
        logger.info("AppelOffre deleted successfully.");
    }

    public void updateStatus(Integer appelOffreId, String status) {
        logger.info("Received request to update status of AppelOffre with ID: {}", appelOffreId);

        AppelOffre appelOffre = appelOffreRepository.findById(appelOffreId)
                .orElseThrow(() -> new EntityNotFoundException("No AppelOffre found with ID: " + appelOffreId));

        if (!status.equals("ACCEPTE") && !status.equals("REFUSE")) {
            throw new IllegalArgumentException("Status must be 'ACCEPTE' or 'REFUSE'");
        }

        appelOffre.setStatus(status);
        appelOffreRepository.save(appelOffre);
        logger.info("AppelOffre status updated successfully.");
    }
}
