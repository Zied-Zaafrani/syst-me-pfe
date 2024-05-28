package com.ziedzaafrani.project.contrat;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.ziedzaafrani.project.appel_offre.AppelOffre;
import com.ziedzaafrani.project.common.BaseEntity;
import com.ziedzaafrani.project.facture.Facture;
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
public class Contrat extends BaseEntity {

    private String reference;
    private String titre;

    @Column(columnDefinition = "TEXT")
    private String description;
    private double cout;
    private LocalDate startDate;
    private LocalDate endDate;

    @Column(columnDefinition = "TEXT")
    private String traveaux;

    @Column(columnDefinition = "TEXT")
    private String livrables;
    private String milestones;
    private String termPaiment;

    @Column(columnDefinition = "TEXT")
    private String clauseDeResiliasion;

    @ManyToOne
    @JoinColumn(name = "appel_offre_id", nullable = false)
    @JsonBackReference
    private AppelOffre appelOffre;

    @OneToMany(mappedBy = "contrat")
    @JsonManagedReference
    private List<Facture> factures;

}


