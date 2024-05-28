package com.ziedzaafrani.project.projet;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record ProjetRequest(
        Integer idRequestDto,
        String referenceRequestDto,
        @NotBlank(message = "Le titre ne peut pas être vide")
        @Size(min = 2, max = 30, message = "Le titre doit avoir entre 2 et 30 caractères")
        String titreRequestDto,
        @NotBlank(message = "La description ne peut pas être vide")
        String descriptionRequestDto,
        @NotNull(message = "La coût ne peut pas être vide")
        Double coutRequestDto,
        @NotNull(message = "La date de début ne peut pas être vide")
        LocalDate startDateRequestDto,
        @NotNull(message = "La date de fin ne peut pas être vide")
        LocalDate endDateRequestDto,
        @NotNull(message = "L'identifiant de la structure ne peut pas être vide")
        Integer structureIdRequestDto
) {
}
