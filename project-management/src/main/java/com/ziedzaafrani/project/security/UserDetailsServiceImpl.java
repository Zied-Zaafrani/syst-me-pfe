package com.ziedzaafrani.project.security;

import com.ziedzaafrani.project.user.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository repository;
    private static final Logger logger = LoggerFactory.getLogger(UserDetailsServiceImpl.class);
    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.info("Loading user by email: {}", username);
        return repository.findByEmail(username)
                .orElseThrow(() -> {
                    logger.error("User not found with email: {}", username);
                    return new UsernameNotFoundException("User not found with email: " + username);
                });
    }
}