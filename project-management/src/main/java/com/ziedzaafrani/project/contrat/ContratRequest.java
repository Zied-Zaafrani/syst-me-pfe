package com.ziedzaafrani.project.contrat;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record ContratRequest(

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
        @NotBlank(message = "Les travaux ne peuvent pas être vides")
        String traveauxRequestDto,
        @NotBlank(message = "Les livrables ne peuvent pas être vides")
        String livrablesRequestDto,
        @NotBlank(message = "Les jalons ne peuvent pas être vides")
        String milestonesRequestDto,
        @NotBlank(message = "Les termes de paiement ne peuvent pas être vides")
        String termPaimentRequestDto,
        @NotBlank(message = "La clause de résiliation ne peut pas être vide")
        String clauseDeResiliasionRequestDto,
        @NotNull(message = "L'identifiant de l'appel d'offre ne peut pas être vide")
        Integer appelOffreIdRequestDto

) {

}
