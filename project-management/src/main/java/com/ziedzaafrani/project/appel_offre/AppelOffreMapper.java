package com.ziedzaafrani.project.appel_offre;

import com.ziedzaafrani.project.projet.Projet;
import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class AppelOffreMapper {

    private static final Logger logger = LoggerFactory.getLogger(AppelOffreMapper.class);

    public AppelOffre toAppelOffre(AppelOffreRequest request, Projet projet) {
        logger.info("Mapping AppelOffreRequest to AppelOffre: {}", request);

        String reference = generateRandomReference();

        AppelOffre appelOffre = AppelOffre.builder()
                .id(request.idRequestDto())
                .reference(reference)
                .titre(request.titreRequestDto())
                .description(request.descriptionRequestDto())
                .cout(request.coutRequestDto())
                .startDate(request.startDateRequestDto())
                .endDate(request.endDateRequestDto())
                .status("EN ATTENTE")
                .traveaux(request.traveauxRequestDto())
                .livrables(request.livrablesRequestDto())
                .soumissionaires(request.soumissionairesRequestDto())
                .contactInformation(request.contactInformationRequestDto())
                .projet(projet)
                .build();
        logger.info("Mapped AppelOffre: {}", appelOffre);
        return appelOffre;
    }

    private String generateRandomReference() {
        return RandomStringUtils.randomAlphanumeric(8);
    }

    public AppelOffreResponse toAppelOffreResponse(AppelOffre appelOffre) {
        logger.info("Mapping AppelOffre to AppelOffreResponse: {}", appelOffre);
        AppelOffreResponse appelOffreResponse = AppelOffreResponse.builder()
                .idResponseDto(appelOffre.getId())
                .referenceResponseDto(appelOffre.getReference())
                .titreResponseDto(appelOffre.getTitre())
                .descriptionResponseDto(appelOffre.getDescription())
                .coutResponseDto(appelOffre.getCout())
                .startDateResponseDto(appelOffre.getStartDate())
                .endDateResponseDto(appelOffre.getEndDate())
                .statusResponseDto(appelOffre.getStatus())
                .traveauxResponseDto(appelOffre.getTraveaux())
                .livrablesResponseDto(appelOffre.getLivrables())
                .soumissionairesResponseDto(appelOffre.getSoumissionaires())
                .contactInformationResponseDto(appelOffre.getContactInformation())
                .projetIdResponseDto(appelOffre.getProjet() != null ? appelOffre.getProjet().getId() : null)
                .build();
        logger.info("Mapped AppelOffreResponse: {}", appelOffreResponse);
        return appelOffreResponse;
    }

}
