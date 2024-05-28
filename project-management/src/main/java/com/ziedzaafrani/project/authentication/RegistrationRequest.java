package com.ziedzaafrani.project.authentication;

import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@Builder
public class RegistrationRequest {

    @NotNull(message = "Le prénom ne peut pas être vide")
    @NotEmpty(message = "Le prénom ne peut pas être vide")
    @Pattern(regexp = "^[a-zA-ZÀ-ÿ-]{1,15}$", message = "Le prénom ne peut contenir que des lettres, au maximum 15 caractères, sans espace")
    private String prenomRequestDto;

    @NotNull(message = "Le nom de famille ne peut pas être vide")
    @NotEmpty(message = "Le nom de famille ne peut pas être vide")
    @Pattern(regexp = "^[a-zA-ZÀ-ÿ ]{1,20}$", message = "Le nom de famille ne peut contenir que des lettres et des espaces, au maximum 20 caractères")
    private String nomRequestDto;

    @Email(message = "L'adresse e-mail n'est pas valide")
    @NotEmpty(message = "L'adresse e-mail ne peut pas être vide")
    @NotNull(message = "L'adresse e-mail ne peut pas être vide")
    private String emailRequestDto;

    @NotEmpty(message = "Le mot de passe ne peut pas être vide")
    @NotNull(message = "Le mot de passe ne peut pas être vide")
    @Size(min = 8, message = "Le mot de passe doit comporter au moins 8 caractères")
    @Size(max = 50, message = "Le mot de passe ne doit pas dépasser 50 caractères")
    private String motDePasseRequestDto;
}

