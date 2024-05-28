package com.ziedzaafrani.project.facture;

import com.ziedzaafrani.project.appel_offre.AppelOffre;
import com.ziedzaafrani.project.appel_offre.AppelOffreMapper;
import com.ziedzaafrani.project.appel_offre.AppelOffreRequest;
import com.ziedzaafrani.project.appel_offre.AppelOffreResponse;
import com.ziedzaafrani.project.contrat.Contrat;
import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class FactureMapper {

    private static final Logger logger = LoggerFactory.getLogger(FactureMapper.class);

    public Facture toFacture(FactureRequest request, Contrat contrat) {

        String reference = generateRandomReference();

        logger.info("Mapping FactureRequest to Facture: {}", request);
        Facture facture = Facture.builder()
                .id(request.idRequestDto())
                .reference(reference)
                .titre(request.titreRequestDto())
                .description(request.descriptionRequestDto())
                .cout(request.coutRequestDto())
                .startDate(request.startDateRequestDto())
                .endDate(request.endDateRequestDto())
                .status(request.statusRequestDto())
                .termePaiment(request.termePaimentRequestDto())
                .informationFacturation(request.informationFacturationRequestDto())
                .contactInformation(request.contactInformationRequestDto())
                .contrat(contrat)
                .build();
        logger.info("Mapped Facture: {}", facture);
        return facture;
    }

    private String generateRandomReference() {
        return RandomStringUtils.randomAlphanumeric(8);
    }

    public FactureResponse toFactureResponse(Facture facture) {
        logger.info("Mapping Facture to FactureResponse: {}", facture);
        FactureResponse factureResponse = FactureResponse.builder()
                .idResponseDto(facture.getId())
                .referenceResponseDto(facture.getReference())
                .titreResponseDto(facture.getTitre())
                .descriptionResponseDto(facture.getDescription())
                .coutResponseDto(facture.getCout())
                .startDateResponseDto(facture.getStartDate())
                .endDateResponseDto(facture.getEndDate())
                .statusResponseDto(facture.getStatus())
                .termePaimentResponseDto(facture.getTermePaiment())
                .informationFacturationResponseDto(facture.getInformationFacturation())
                .contactInformationResponseDto(facture.getContactInformation())
                .contratIdResponseDto(facture.getContrat() != null ? facture.getContrat().getId() : null)
                .build();
        logger.info("Mapped FactureResponse: {}", factureResponse);
        return factureResponse;
    }

}
