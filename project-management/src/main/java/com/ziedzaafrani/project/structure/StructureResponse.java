package com.ziedzaafrani.project.structure;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StructureResponse {

    private Integer idResponseDto;
    private String referenceResponseDto;
    private String titreResponseDto;
    private String descriptionResponseDto;

}
