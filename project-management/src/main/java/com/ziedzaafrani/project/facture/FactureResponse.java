package com.ziedzaafrani.project.facture;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FactureResponse {
    private Integer idResponseDto;
    private String referenceResponseDto;
    private String titreResponseDto;
    private String descriptionResponseDto;
    private Double coutResponseDto;
    private LocalDate startDateResponseDto;
    private LocalDate endDateResponseDto;
    private String statusResponseDto;
    private String termePaimentResponseDto;
    private String informationFacturationResponseDto;
    private String contactInformationResponseDto;
    private Integer contratIdResponseDto;

}
