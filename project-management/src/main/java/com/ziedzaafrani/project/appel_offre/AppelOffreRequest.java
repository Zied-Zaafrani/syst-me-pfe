package com.ziedzaafrani.project.appel_offre;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record AppelOffreRequest(
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
        String statusRequestDto,
        @NotBlank(message = "Les travaux ne peuvent pas être vides")
        String traveauxRequestDto,
        @NotBlank(message = "Les livrables ne peuvent pas être vides")
        String livrablesRequestDto,
        @NotBlank(message = "Les soumissionnaires ne peuvent pas être vides")
        String soumissionairesRequestDto,
        @NotBlank(message = "Les informations de contact ne peuvent pas être vides")
        String contactInformationRequestDto,
        @NotNull(message = "L'identifiant du projet ne peut pas être vide")
        Integer projetIdRequestDto
) {
}
