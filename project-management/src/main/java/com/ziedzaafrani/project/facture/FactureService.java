package com.ziedzaafrani.project.facture;

import com.ziedzaafrani.project.appel_offre.AppelOffre;
import com.ziedzaafrani.project.appel_offre.AppelOffreRepository;
import com.ziedzaafrani.project.contrat.Contrat;
import com.ziedzaafrani.project.contrat.ContratRepository;
import com.ziedzaafrani.project.common.PageResponse;
import com.ziedzaafrani.project.projet.Projet;
import com.ziedzaafrani.project.projet.ProjetRepository;
import com.ziedzaafrani.project.structure.Structure;
import com.ziedzaafrani.project.user.User;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
public class FactureService {

    private static final Logger logger = LoggerFactory.getLogger(FactureService.class);

    private final FactureRepository factureRepository;
    private final ContratRepository contratRepository;
    private final FactureMapper factureMapper;
    private final ProjetRepository projetRepository;
    private final AppelOffreRepository appelOffreRepository;


    public FactureResponse createFacture(FactureRequest request, Authentication connectedUser) {
        logger.info("Creating new Facture. Title: {}", request.titreRequestDto());

        // Retrieve current user
        User user = (User) connectedUser.getPrincipal();
        logger.info("Current user: {}", user.getEmail());

        // Retrieve the user's structure
        Structure userStructure = user.getStructure();
        logger.info("User's structure: {}", userStructure.getTitre());

        // Retrieve the Contrat
        Contrat contrat = contratRepository.findById(request.contratIdRequestDto())
                .orElseThrow(() -> {
                    logger.error("Contrat not found with id: {}", request.contratIdRequestDto());
                    return new EntityNotFoundException("Contrat not found");
                });

        // Check if a Facture with the same title exists in the same structure
        if (factureRepository.existsByTitreAndContrat(request.titreRequestDto(), contrat)) {
            logger.warn("A Facture with the same title already exists in this contrat: {}", contrat.getTitre());
            throw new IllegalArgumentException("A Facture with the same title already exists in this contrat");
        }

        // Map request to Facture
        Facture facture = factureMapper.toFacture(request, contrat);

        // Save the Facture
        Facture savedFacture = factureRepository.save(facture);
        FactureResponse response = factureMapper.toFactureResponse(savedFacture);

        logger.info("Created Facture successfully with ID: {}", savedFacture.getId());

        return response;
    }

    public FactureResponse getFactureById(Integer id) {
        logger.info("Fetching Facture by id: {}", id);
        Facture facture = factureRepository.findById(id)
                .orElseThrow(() -> {
                    logger.error("Facture not found with id: {}", id);
                    return new EntityNotFoundException("Facture not found");
                });
        FactureResponse response = factureMapper.toFactureResponse(facture);
        logger.info("Fetched Facture: {}", response);
        return response;
    }

    public PageResponse<FactureResponse> getFacturesByStructureId(int page, int size, Authentication connectedUser) {
        logger.info("Fetching Factures by structure id");

        // Retrieve current user
        User user = (User) connectedUser.getPrincipal();
        logger.info("Current user: {}", user.getEmail());

        // Retrieve the user's structure
        Structure userStructure = user.getStructure();
        logger.info("User's structure: {}", userStructure.getTitre());

        if (userStructure == null) {
            throw new IllegalStateException("User is not associated with any structure.");
        }

        Pageable pageable = PageRequest.of(page, size, Sort.by("creationDate").descending());

        Page<Facture> factures = factureRepository.findByStructure(userStructure, pageable);

        List<FactureResponse> responses = factures.getContent().stream()
                .map(factureMapper::toFactureResponse)
                .collect(Collectors.toList());

        logger.info("Fetched Factures: {}", responses);
        return new PageResponse<>(
                responses,
                factures.getNumber(),
                factures.getSize(),
                factures.getTotalElements(),
                factures.getTotalPages(),
                factures.isFirst(),
                factures.isLast()
        );
    }

    public PageResponse<FactureResponse> getFacturesByContratId(Integer contratId, int page, int size) {
        logger.info("Fetching Factures by Contrat ID: {}", contratId);

        Contrat contrat = contratRepository.findById(contratId)
                .orElseThrow(() -> {
                    logger.error("No Contrat found with ID: {}", contratId);
                    return new EntityNotFoundException("No Contrat found with ID: " + contratId);
                });

        Pageable pageable = PageRequest.of(page, size, Sort.by("creationDate").descending());

        Page<Facture> factures = factureRepository.findByContrat(contrat, pageable);

        List<FactureResponse> responses = factures.getContent().stream()
                .map(factureMapper::toFactureResponse)
                .collect(Collectors.toList());

        logger.info("Fetched Factures by Contrat ID {}: {}", contratId, responses);
        return new PageResponse<>(
                responses,
                factures.getNumber(),
                factures.getSize(),
                factures.getTotalElements(),
                factures.getTotalPages(),
                factures.isFirst(),
                factures.isLast()
        );
    }

    public PageResponse<FactureResponse> getFacturesByAppelOffreId(Integer appelOffreId, int page, int size) {
        logger.info("Fetching Factures by AppelOffre ID: {}", appelOffreId);

        AppelOffre appelOffre = appelOffreRepository.findById(appelOffreId)
                .orElseThrow(() -> {
                    logger.error("No AppelOffre found with ID: {}", appelOffreId);
                    return new EntityNotFoundException("No AppelOffre found with ID: " + appelOffreId);
                });

        Pageable pageable = PageRequest.of(page, size, Sort.by("creationDate").descending());

        Page<Facture> factures = factureRepository.findByAppelOffre(appelOffre, pageable);

        List<FactureResponse> responses = factures.getContent().stream()
                .map(factureMapper::toFactureResponse)
                .collect(Collectors.toList());

        logger.info("Fetched Factures by AppelOffre ID {}: {}", appelOffreId, responses);
        return new PageResponse<>(
                responses,
                factures.getNumber(),
                factures.getSize(),
                factures.getTotalElements(),
                factures.getTotalPages(),
                factures.isFirst(),
                factures.isLast()
        );
    }

    public PageResponse<FactureResponse> getFacturesByProjetId(Integer projetId, int page, int size) {
        logger.info("Fetching Factures by Projet ID: {}", projetId);

        Projet projet = projetRepository.findById(projetId)
                .orElseThrow(() -> {
                    logger.error("No Projet found with ID: {}", projetId);
                    return new EntityNotFoundException("No Projet found with ID: " + projetId);
                });

        Pageable pageable = PageRequest.of(page, size, Sort.by("creationDate").descending());

        Page<Facture> factures = factureRepository.findByProjet(projet, pageable);

        List<FactureResponse> responses = factures.getContent().stream()
                .map(factureMapper::toFactureResponse)
                .collect(Collectors.toList());

        logger.info("Fetched Factures by Projet ID {}: {}", projetId, responses);
        return new PageResponse<>(
                responses,
                factures.getNumber(),
                factures.getSize(),
                factures.getTotalElements(),
                factures.getTotalPages(),
                factures.isFirst(),
                factures.isLast()
        );
    }
}

