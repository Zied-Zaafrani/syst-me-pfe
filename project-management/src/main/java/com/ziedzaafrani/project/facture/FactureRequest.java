package com.ziedzaafrani.project.facture;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record FactureRequest(
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
        @NotBlank(message = "Le statut ne peut pas être vide")
        String statusRequestDto,
        @NotBlank(message = "Les termes de paiement ne peuvent pas être vides")
        String termePaimentRequestDto,
        @NotBlank(message = "Les informations de facturation ne peuvent pas être vides")
        String informationFacturationRequestDto,
        @NotBlank(message = "Les informations de contact ne peuvent pas être vides")
        String contactInformationRequestDto,
        @NotNull(message = "L'identifiant du contrat ne peut pas être vide")
        Integer contratIdRequestDto
) {
}
