package com.ziedzaafrani.project.structure;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record StructureRequest(

        Integer idRequestDto,
        String referenceRequestDto,
        @NotBlank(message = "Le titre ne peut pas être vide")
        @Size(min = 2, max = 30, message = "Le titre doit avoir entre 2 et 30 caractères")
        String titreRequestDto,
        @NotBlank(message = "La description ne peut pas être vide")
        String descriptionRequestDto

) {

}
