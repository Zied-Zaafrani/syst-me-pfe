package com.ziedzaafrani.project.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.ziedzaafrani.project.user.User;
import org.springframework.data.domain.AuditorAware;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

public class ApplicationAuditAware implements AuditorAware<Integer> {
    private static final Logger logger = LogManager.getLogger(ApplicationAuditAware.class);

    @Override
    public Optional<Integer> getCurrentAuditor() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null ||
                !authentication.isAuthenticated() ||
                authentication instanceof AnonymousAuthenticationToken) {
            logger.debug("No authenticated user found for auditing.");
            return Optional.empty();
        }

        User userPrincipal = (User) authentication.getPrincipal();

        logger.debug("Auditing performed by user with ID: {}", userPrincipal.getId());
        return Optional.ofNullable(userPrincipal.getId());
    }
}
