package com.ziedzaafrani.project.projet;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.ziedzaafrani.project.common.PageResponse;
import com.ziedzaafrani.project.structure.Structure;
import com.ziedzaafrani.project.user.User;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProjetService {

    private static final Logger logger = LogManager.getLogger(ProjetService.class);

    private final ProjetRepository projetRepository;
    private final ProjetMapper projetMapper;

    public Integer save(ProjetRequest request, Authentication connectedUser) {
        logger.info("Received request to save a project. Title: {}", request.titreRequestDto());

        User user = ((User) connectedUser.getPrincipal());
        logger.info("Current user: {}", user.getEmail());

        Structure userStructure = user.getStructure();
        logger.info("User's structure: {}", userStructure.getTitre());

        if (projetRepository.existsByTitreAndStructure(request.titreRequestDto(), userStructure)) {
            logger.warn("A project with the same title already exists in this structure: {}", userStructure.getTitre());
            throw new IllegalArgumentException("A project with the same title already exists in this structure");
        }

        // Map request to project
        Projet projet = projetMapper.toProjet(request, userStructure);
        logger.info("Mapped project: {}", projet.getTitre());

        // Save the project
        Projet savedProjet = projetRepository.save(projet);
        Integer projetId = savedProjet.getId();

        logger.info("Project saved successfully with ID: {}", projetId);

        return projetId;
    }

    public ProjetResponse findProjetById(Integer projetId) {
        logger.info("Received request to find a project by ID: {}", projetId);

        return projetRepository.findById(projetId)
                .map(projetMapper::toProjetResponse)
                .orElseThrow(() -> new EntityNotFoundException("No Project found with ID: " + projetId));
    }

    public PageResponse<ProjetResponse> findAllProjetsByUserStructure(int page, int size, Authentication connectedUser) {
        logger.info("Received request to find all projects by user's structure. Page: {}, Size: {}", page, size);

        User user = ((User) connectedUser.getPrincipal());
        logger.info("the current connected user. id: {}, structure: {}", user.getId(), user.getStructure());

        Structure userStructure = user.getStructure();

        if (userStructure == null) {
            throw new IllegalStateException("User is not associated with any structure.");
        }

        Pageable pageable = PageRequest.of(page, size, Sort.by("creationDate").descending());
        logger.info("checking find by structure {}, id : {}", userStructure, userStructure.getId());

        Page<Projet> projets = projetRepository.findByStructure(userStructure, pageable);

        List<ProjetResponse> projetResponses = projets.getContent().stream()
                .map(projetMapper::toProjetResponse)
                .collect(Collectors.toList());

        logger.info("All projects retrieved successfully.");
        return new PageResponse<>(
                projetResponses,
                projets.getNumber(),
                projets.getSize(),
                projets.getTotalElements(),
                projets.getTotalPages(),
                projets.isFirst(),
                projets.isLast()
        );
    }


    public PageResponse<ProjetResponse> findAllProjets(int page, int size) {
        logger.info("Received request to find all projects.");

        Pageable pageable = PageRequest.of(page, size, Sort.by("creationDate").descending());
        Page<Projet> projets = projetRepository.findAll(pageable);

        List<ProjetResponse> projetResponses = projets.stream()
                .map(projetMapper::toProjetResponse)
                .collect(Collectors.toList());

        return new PageResponse<>(
                projetResponses,
                projets.getNumber(),
                projets.getSize(),
                projets.getTotalElements(),
                projets.getTotalPages(),
                projets.isFirst(),
                projets.isLast()
        );
    }

    public void delete(Integer projetId) {
        logger.info("Received request to delete project with ID: {}", projetId);

        Projet projet = projetRepository.findById(projetId)
                .orElseThrow(() -> new EntityNotFoundException("No Project found with ID: " + projetId));

        projetRepository.delete(projet);
        logger.info("Project deleted successfully.");
    }
}