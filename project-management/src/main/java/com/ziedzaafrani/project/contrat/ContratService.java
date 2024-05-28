package com.ziedzaafrani.project.contrat;

import com.ziedzaafrani.project.appel_offre.AppelOffre;
import com.ziedzaafrani.project.appel_offre.AppelOffreRepository;
import com.ziedzaafrani.project.common.PageResponse;
import com.ziedzaafrani.project.projet.Projet;
import com.ziedzaafrani.project.projet.ProjetRepository;
import com.ziedzaafrani.project.structure.Structure;
import com.ziedzaafrani.project.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;

import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ContratService {

    private static final Logger logger = LoggerFactory.getLogger(ContratService.class);

    private final ContratRepository contratRepository;
    private final AppelOffreRepository appelOffreRepository;
    private final ProjetRepository projetRepository;
    private final ContratMapper contratMapper;

    public Integer save(ContratRequest request, Authentication connectedUser) {
        logger.info("Creating new Contrat. Title: {}", request.titreRequestDto());

        // Retrieve current user
        User user = (User) connectedUser.getPrincipal();
        logger.info("Current user: {}", user.getEmail());

        // Retrieve the user's structure
        Structure userStructure = user.getStructure();
        logger.info("User's structure: {}", userStructure.getTitre());

        // Retrieve the AppelOffre
        AppelOffre appelOffre = appelOffreRepository.findById(request.appelOffreIdRequestDto())
                .orElseThrow(() -> {
                    logger.error("Appel d'offre not found with id: {}", request.appelOffreIdRequestDto());
                    return new EntityNotFoundException("Appel d'offre not found");
                });

        // Check if a Contrat with the same title exists in the same AppelOffre
        if (contratRepository.existsByTitreAndAppelOffre(request.titreRequestDto(), appelOffre)) {
            logger.warn("A Contrat with the same title already exists in this AppelOffre: {}", appelOffre.getTitre());
            throw new IllegalArgumentException("A Contrat with the same title already exists in this AppelOffre");
        }

        // Map request to Contrat
        Contrat contrat = contratMapper.toContrat(request, appelOffre);

        // Save the Contrat
        Contrat savedContrat = contratRepository.save(contrat);
        Integer contratId = savedContrat.getId();

        logger.info("Created Contrat successfully with ID: {}", contratId);
        return contratId;
    }

    public ContratResponse findContratById(Integer id) {
        logger.info("Fetching Contrat by id: {}", id);
        Contrat contrat = contratRepository.findById(id)
                .orElseThrow(() -> {
                    logger.error("Contrat not found with id: {}", id);
                    return new EntityNotFoundException("Contrat not found");
                });
        ContratResponse response = contratMapper.toContratResponse(contrat);
        logger.info("Fetched Contrat: {}", response);
        return response;
    }

    public PageResponse<ContratResponse> findAllContratsByUserStructure(int page, int size, Authentication connectedUser) {
        logger.info("Received request to find all Contrats by user's structure. Page: {}, Size: {}", page, size);

        User user = (User) connectedUser.getPrincipal();
        logger.info("The current connected user. ID: {}, Structure: {}", user.getId(), user.getStructure());

        Structure userStructure = user.getStructure();

        if (userStructure == null) {
            throw new IllegalStateException("User is not associated with any structure.");
        }

        Pageable pageable = PageRequest.of(page, size, Sort.by("creationDate").descending());
        logger.info("Checking find by structure {}, ID: {}", userStructure, userStructure.getId());

        Page<Contrat> contratsPage = contratRepository.findByStructure(userStructure, pageable);

        List<ContratResponse> contratResponses = contratsPage.getContent().stream()
                .map(contratMapper::toContratResponse)
                .collect(Collectors.toList());

        logger.info("All Contrats retrieved successfully.");
        return new PageResponse<>(
                contratResponses,
                contratsPage.getNumber(),
                contratsPage.getSize(),
                contratsPage.getTotalElements(),
                contratsPage.getTotalPages(),
                contratsPage.isFirst(),
                contratsPage.isLast()
        );
    }

    public PageResponse<ContratResponse> findAllContratsByAppelOffreId(Integer appelOffreId, int page, int size) {
        logger.info("Received request to find all Contrats by AppelOffre ID: {}. Page: {}, Size: {}", appelOffreId, page, size);

        AppelOffre appelOffre = appelOffreRepository.findById(appelOffreId)
                .orElseThrow(() -> {
                    logger.error("No AppelOffre found with ID: {}", appelOffreId);
                    return new EntityNotFoundException("No AppelOffre found with ID: " + appelOffreId);
                });

        Pageable pageable = PageRequest.of(page, size, Sort.by("creationDate").descending());
        logger.info("Retrieving Contrats by AppelOffre ID: {}", appelOffreId);

        Page<Contrat> contratsPage = contratRepository.findByAppelOffre(appelOffre, pageable);

        List<ContratResponse> contratResponses = contratsPage.getContent().stream()
                .map(contratMapper::toContratResponse)
                .collect(Collectors.toList());

        logger.info("All Contrats retrieved successfully.");
        return new PageResponse<>(
                contratResponses,
                contratsPage.getNumber(),
                contratsPage.getSize(),
                contratsPage.getTotalElements(),
                contratsPage.getTotalPages(),
                contratsPage.isFirst(),
                contratsPage.isLast()
        );
    }

    public PageResponse<ContratResponse> findAllContratsByProjetId(Integer projetId, int page, int size) {
        logger.info("Received request to find all Contrats by Projet ID: {}. Page: {}, Size: {}", projetId, page, size);

        Projet projet = projetRepository.findById(projetId)
                .orElseThrow(() -> {
                    logger.error("No Projet found with ID: {}", projetId);
                    return new EntityNotFoundException("No Projet found with ID: " + projetId);
                });

        Pageable pageable = PageRequest.of(page, size, Sort.by("creationDate").descending());
        logger.info("Retrieving Contrats by Projet ID: {}", projetId);

        Page<Contrat> contratsPage = contratRepository.findByProjet(projet, pageable);

        List<ContratResponse> contratResponses = contratsPage.getContent().stream()
                .map(contratMapper::toContratResponse)
                .collect(Collectors.toList());

        logger.info("All Contrats retrieved successfully.");
        return new PageResponse<>(
                contratResponses,
                contratsPage.getNumber(),
                contratsPage.getSize(),
                contratsPage.getTotalElements(),
                contratsPage.getTotalPages(),
                contratsPage.isFirst(),
                contratsPage.isLast()
        );
    }
}
