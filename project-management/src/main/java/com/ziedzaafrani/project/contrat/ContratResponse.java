package com.ziedzaafrani.project.contrat;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ContratResponse {

    private Integer idResponseDto;
    private String referenceResponseDto;
    private String titreResponseDto;
    private String descriptionResponseDto;
    private Double coutResponseDto;
    private LocalDate startDateResponseDto;
    private LocalDate endDateResponseDto;
    private String traveauxResponseDto;
    private String livrablesResponseDto;
    private String milestonesResponseDto;
    private String termPaimentResponseDto;
    private String clauseDeResiliasionResponseDto;
    private Integer appelOffreIdResponseDto;
}
