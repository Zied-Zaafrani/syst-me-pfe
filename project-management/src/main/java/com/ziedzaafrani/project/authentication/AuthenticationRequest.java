package com.ziedzaafrani.project.authentication;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class AuthenticationRequest {

    @Email(message = "Veuillez fournir une adresse e-mail valide")
    @NotEmpty(message = "L'e-mail est obligatoire")
    @NotNull(message = "L'e-mail est obligatoire")
    private String emailRequestDto;

    @NotEmpty(message = "Le mot de passe est obligatoire")
    @NotNull(message = "Le mot de passe est obligatoire")
    @Size(min = 8, message = "Le mot de passe doit contenir au moins 8 caractères")
    @Size(max = 50, message = "Le mot de passe ne doit pas dépasser 50 caractères")
    private String motDePassRequestDto;
}