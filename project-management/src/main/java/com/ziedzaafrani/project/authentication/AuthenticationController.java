package com.ziedzaafrani.project.authentication;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("auth")
@RequiredArgsConstructor
@Tag(name = "Authentication")
public class AuthenticationController {

    private final AuthenticationService service;
    private static final Logger logger = LogManager.getLogger(AuthenticationController.class);

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<?> register(@RequestBody @Valid RegistrationRequest request) throws MessagingException {
        logger.info("Received registration request for email: {}", request.getEmailRequestDto());
        service.register(request);
        logger.info("Registration request processed successfully for email: {}", request.getEmailRequestDto());
        return ResponseEntity.accepted().build();
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
        logger.info("Received authentication request for email: {}", request.getEmailRequestDto());
        AuthenticationResponse response = service.authenticate(request);
        logger.info("Authentication request processed successfully for email: {}", request.getEmailRequestDto());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/activate-account")
    public void confirm(@RequestParam String token) throws MessagingException {
        logger.info("Received account activation request with token: {}", token);
        service.activateAccount(token);
        logger.info("Account activated successfully for token: {}", token);
    }

    @PostMapping("/grant-admin")
    public ResponseEntity<?> grantAdminRole(@RequestBody @Valid AuthenticationRequest request) {
        logger.info("Received request to grant admin role for email: {}", request.getEmailRequestDto());
        service.grantAdminRole(request.getEmailRequestDto());
        logger.info("Admin role granted successfully for email: {}", request.getEmailRequestDto());
        return ResponseEntity.ok().build();
    }

    @PostMapping("/lock-account")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> lockAccount(@RequestBody @Valid AuthenticationRequest request) {
        logger.info("Received request to lock account for email: {}", request.getEmailRequestDto());
        service.lockAccount(request.getEmailRequestDto());
        logger.info("Account locked successfully for email: {}", request.getEmailRequestDto());
        return ResponseEntity.ok().build();
    }

    @PostMapping("/unlock-account")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> unlockAccount(@RequestBody @Valid AuthenticationRequest request) {
        logger.info("Received request to unlock account for email: {}", request.getEmailRequestDto());
        service.unlockAccount(request.getEmailRequestDto());
        logger.info("Account unlocked successfully for email: {}", request.getEmailRequestDto());
        return ResponseEntity.ok().build();
    }
}
