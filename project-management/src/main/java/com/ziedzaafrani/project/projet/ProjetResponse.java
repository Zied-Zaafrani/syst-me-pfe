package com.ziedzaafrani.project.projet;

import lombok.*;

import java.time.LocalDate;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProjetResponse {

    private Integer idResponseDto;
    private String referenceResponseDto;
    private String titreResponseDto;
    private String descriptionResponseDto;
    private Double coutResponseDto;
    private LocalDate startDateResponseDto;
    private LocalDate endDateResponseDto;
    private Integer structureIdResponseDto;

}
