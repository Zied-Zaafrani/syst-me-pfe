package com.ziedzaafrani.project.projet;

import com.ziedzaafrani.project.structure.Structure;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.springframework.stereotype.Service;

@Service
public class ProjetMapper {

    private static final Logger logger = LogManager.getLogger(ProjetMapper.class);

    public Projet toProjet(ProjetRequest request, Structure structure) {
        logger.debug("Mapping ProjetRequest to Projet entity.");

        String reference = generateRandomReference();

        return Projet.builder()
                .reference(reference)
                .titre(request.titreRequestDto())
                .description(request.descriptionRequestDto())
                .cout(request.coutRequestDto())
                .startDate(request.startDateRequestDto())
                .endDate(request.endDateRequestDto())
                .structure(structure)
                .build();
    }

    private String generateRandomReference() {
        return RandomStringUtils.randomAlphanumeric(8);
    }

    public ProjetResponse toProjetResponse(Projet projet) {
        logger.debug("Mapping Projet entity to ProjetResponse.");

        return ProjetResponse.builder()
                .idResponseDto(projet.getId())
                .referenceResponseDto(projet.getReference())
                .titreResponseDto(projet.getTitre())
                .descriptionResponseDto(projet.getDescription())
                .coutResponseDto(projet.getCout())
                .startDateResponseDto(projet.getStartDate())
                .endDateResponseDto(projet.getEndDate())
                .structureIdResponseDto(projet.getStructure() != null ? projet.getStructure().getId() : null)
                .build();
    }

}