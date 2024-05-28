package com.ziedzaafrani.project.appel_offre;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.ziedzaafrani.project.common.BaseEntity;
import com.ziedzaafrani.project.contrat.Contrat;
import com.ziedzaafrani.project.projet.Projet;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class AppelOffre extends BaseEntity {

        private String reference;
        private String titre;

        @Column(columnDefinition = "TEXT")
        private String description;
        private double cout;
        private LocalDate startDate;
        private LocalDate endDate;
        private String status;

        @Column(columnDefinition = "TEXT")
        private String traveaux;

        @Column(columnDefinition = "TEXT")
        private String livrables;

        @Column(columnDefinition = "TEXT")
        private String soumissionaires;

        @Column(columnDefinition = "TEXT")
        private String contactInformation;

        @ManyToOne
        @JoinColumn(name = "projet_id", nullable = false)
        @JsonBackReference
        private Projet projet;

        @OneToMany(mappedBy = "appelOffre")
        @JsonManagedReference
        private List<Contrat> contrats;
}
