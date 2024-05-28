package com.ziedzaafrani.project.contrat;

import com.ziedzaafrani.project.appel_offre.AppelOffre;
import com.ziedzaafrani.project.appel_offre.AppelOffreMapper;
import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ContratMapper {

    private static final Logger logger = LoggerFactory.getLogger(ContratMapper.class);

    public Contrat toContrat(ContratRequest request, AppelOffre appelOffre) {
        logger.info("Mapping ContratRequest to Contrat: {}", request);

        String reference = generateRandomReference();

        Contrat contrat = Contrat.builder()
                .id(request.idRequestDto())
                .reference(reference)
                .titre(request.titreRequestDto())
                .description(request.descriptionRequestDto())
                .cout(request.coutRequestDto())
                .startDate(request.startDateRequestDto())
                .endDate(request.endDateRequestDto())
                .traveaux(request.traveauxRequestDto())
                .livrables(request.livrablesRequestDto())
                .milestones(request.milestonesRequestDto())
                .termPaiment(request.termPaimentRequestDto())
                .clauseDeResiliasion(request.clauseDeResiliasionRequestDto())
                .appelOffre(appelOffre)
                .build();
        logger.info("Mapped Contrat: {}", contrat);
        return contrat;
    }

    private String generateRandomReference() {
        return RandomStringUtils.randomAlphanumeric(8);
    }

    public ContratResponse toContratResponse(Contrat contrat) {
        logger.info("Mapping Contrat to ContratResponse: {}", contrat);
        ContratResponse contratResponse = ContratResponse.builder()
                .idResponseDto(contrat.getId())
                .referenceResponseDto(contrat.getReference())
                .titreResponseDto(contrat.getTitre())
                .descriptionResponseDto(contrat.getDescription())
                .coutResponseDto(contrat.getCout())
                .startDateResponseDto(contrat.getStartDate())
                .endDateResponseDto(contrat.getEndDate())
                .traveauxResponseDto(contrat.getTraveaux())
                .livrablesResponseDto(contrat.getLivrables())
                .milestonesResponseDto(contrat.getMilestones())
                .termPaimentResponseDto(contrat.getTermPaiment())
                .clauseDeResiliasionResponseDto(contrat.getClauseDeResiliasion())
                .appelOffreIdResponseDto(contrat.getAppelOffre() != null ? contrat.getAppelOffre().getId() : null)
                .build();
        logger.info("Mapped ContratResponse: {}", contratResponse);
        return contratResponse;
    }

}
