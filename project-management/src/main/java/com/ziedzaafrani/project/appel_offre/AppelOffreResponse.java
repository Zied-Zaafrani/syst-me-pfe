package com.ziedzaafrani.project.appel_offre;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AppelOffreResponse {

    private Integer idResponseDto;
    private String referenceResponseDto;
    private String titreResponseDto;
    private String descriptionResponseDto;
    private Double coutResponseDto;
    private LocalDate startDateResponseDto;
    private LocalDate endDateResponseDto;
    private String statusResponseDto;
    private String traveauxResponseDto;
    private String livrablesResponseDto;
    private String soumissionairesResponseDto;
    private String contactInformationResponseDto;
    private Integer projetIdResponseDto;
}
