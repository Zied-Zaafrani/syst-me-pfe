package com.ziedzaafrani.project.authentication;

import com.ziedzaafrani.project.email.EmailService;
import com.ziedzaafrani.project.email.EmailTemplateName;
import com.ziedzaafrani.project.role.RoleRepository;
import com.ziedzaafrani.project.security.JwtService;
import com.ziedzaafrani.project.structure.StructureRepository;
import com.ziedzaafrani.project.user.Token;
import com.ziedzaafrani.project.user.TokenRepository;
import com.ziedzaafrani.project.user.User;
import com.ziedzaafrani.project.user.UserRepository;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;



@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final RoleRepository roleRepository;
    private final EmailService emailService;
    private final TokenRepository tokenRepository;
    private final StructureRepository structureRepository;
    private static final Logger logger = LogManager.getLogger(AuthenticationService.class);

    @Value("${application.mailing.frontend.activation-url}")
    private String activationUrl;

    public void register(RegistrationRequest request) throws MessagingException {
        logger.info("Registering user with email: {}", request.getEmailRequestDto());

        var userRole = roleRepository.findBynomRole("USER")
                .orElseThrow(() -> new IllegalStateException("ROLE USER was not initiated"));

        var structure = structureRepository.findById(2) // Retrieve the structure with ID 1
                .orElseThrow(() -> new IllegalStateException("Structure with ID 1 not found"));

        var user = User.builder()
                .nom(request.getPrenomRequestDto())
                .prenom(request.getNomRequestDto())
                .email(request.getEmailRequestDto())
                .motDePasse(passwordEncoder.encode(request.getMotDePasseRequestDto()))
                .compteBlocke(false)
                .compteValide(false)
                .roles(List.of(userRole))
                .structure(structure)
                .build();
        userRepository.save(user);

        logger.info("User registered and saved with email: {}", user.getEmail());
        sendValidationEmail(user);
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        logger.info("Authenticating user with email: {}", request.getEmailRequestDto());
        logger.info("Authenticating user with mtp: {}", request.getMotDePassRequestDto());


        var auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmailRequestDto(),
                        request.getMotDePassRequestDto()
                )
        );

        var claims = new HashMap<String, Object>();
        var user = ((User) auth.getPrincipal());
        claims.put("fullName", user.getFullName());

        var jwtToken = jwtService.generateToken(claims, (User) auth.getPrincipal());
        return AuthenticationResponse.builder()
                .tokenResponseDto(jwtToken)
                .build();
    }

    public void activateAccount(String token) throws MessagingException {
        logger.info("Activating account with token: {}", token);

        Token savedToken = tokenRepository.findByToken(token)
                .orElseThrow(() -> new RuntimeException("Invalid token"));

        if (LocalDateTime.now().isAfter(savedToken.getExpiresAt())) {
            sendValidationEmail(savedToken.getUser());
            throw new RuntimeException("Activation token has expired. A new token has been sent to the same email address");
        }

        var user = userRepository.findById(savedToken.getUser().getId())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        user.setCompteValide(true);
        userRepository.save(user);

        savedToken.setValidatedAt(LocalDateTime.now());
        tokenRepository.save(savedToken);

        logger.info("Account activated successfully for token: {}", token);
    }

    private String generateAndSaveActivationToken(User user) {
        // Generate a token
        String generatedToken = generateActivationCode(6);
        var token = Token.builder()
                .token(generatedToken)
                .createdAt(LocalDateTime.now())
                .expiresAt(LocalDateTime.now().plusMinutes(15))
                .user(user)
                .build();
        tokenRepository.save(token);

        return generatedToken;
    }

    private void sendValidationEmail(User user) throws MessagingException {
        logger.info("Sending validation email to user with email: {}", user.getEmail());

        var newToken = generateAndSaveActivationToken(user);

        emailService.sendEmail(
                user.getEmail(),
                user.getFullName(),
                EmailTemplateName.ACTIVATE_ACCOUNT,
                activationUrl,
                newToken,
                "Account activation"
        );
    }

    private String generateActivationCode(int length) {
        String characters = "0123456789";
        StringBuilder codeBuilder = new StringBuilder();

        SecureRandom secureRandom = new SecureRandom();

        for (int i = 0; i < length; i++) {
            int randomIndex = secureRandom.nextInt(characters.length());
            codeBuilder.append(characters.charAt(randomIndex));
        }

        return codeBuilder.toString();
    }

    public void grantAdminRole(String email) {
        logger.info("Granting admin role to user with email: {}", email);

        var user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        var adminRole = roleRepository.findBynomRole("ADMIN")
                .orElseThrow(() -> new IllegalStateException("ROLE ADMIN was not initiated"));

        if (!user.getRoles().contains(adminRole)) {
            user.getRoles().add(adminRole);
            userRepository.save(user);
            logger.info("Admin role granted successfully to user with email: {}", email);
        } else {
            throw new RuntimeException("User is already an ADMIN");
        }
    }

    public void lockAccount(String email) {
        logger.info("Locking account for user with email: {}", email);

        var user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        user.setCompteBlocke(true);
        userRepository.save(user);

        logger.info("Account locked successfully for user with email: {}", email);
    }

    public void unlockAccount(String email) {
        logger.info("Unlocking account for user with email: {}", email);

        var user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        user.setCompteBlocke(false);
        userRepository.save(user);

        logger.info("Account unlocked successfully for user with email: {}", email);
    }
}
