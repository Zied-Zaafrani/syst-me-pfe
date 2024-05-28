package com.ziedzaafrani.project.user;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.ziedzaafrani.project.role.Role;
import com.ziedzaafrani.project.structure.Structure;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.security.Principal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static jakarta.persistence.FetchType.EAGER;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "_user")
@EntityListeners(AuditingEntityListener.class)
public class User implements UserDetails, Principal {

    @Id
    @GeneratedValue
    private Integer id;
    private String prenom;
    private String nom;
    private LocalDate dateNaiss;
    @Column(unique = true)
    private String email;
    private String motDePasse;
    private boolean compteBlocke;
    private boolean compteValide;

    @ManyToMany(fetch = EAGER)
    private List<Role> roles;

    @ManyToOne
    @JoinColumn(name = "structure_id", nullable = false)
    @JsonBackReference
    private Structure structure;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdDate;

    @LastModifiedDate
    @Column(insertable = false)
    private LocalDateTime lastModifiedDate;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles
                .stream()
                .map(r -> new SimpleGrantedAuthority(r.getNomRole()))
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return motDePasse;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !compteBlocke;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return compteValide;
    }

    @Override
    public String getName() {
        return email;
    }

    public String getFullName() {
        return getNom() + " " + getPrenom();
    }
}