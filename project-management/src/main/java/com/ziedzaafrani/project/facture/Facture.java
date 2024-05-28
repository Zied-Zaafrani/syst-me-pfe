package com.ziedzaafrani.project.facture;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.ziedzaafrani.project.common.BaseEntity;
import com.ziedzaafrani.project.contrat.Contrat;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class Facture extends BaseEntity {

    private String reference;
    private String titre;

    @Column(columnDefinition = "TEXT")
    private String description;
    private double cout;
    private LocalDate startDate;
    private LocalDate endDate;

    private String status;

    @Column(columnDefinition = "TEXT")
    private String termePaiment;

    @Column(columnDefinition = "TEXT")
    private String informationFacturation;

    @Column(columnDefinition = "TEXT")
    private String contactInformation;

    @ManyToOne
    @JoinColumn(name = "contrat_id", nullable = false)
    @JsonBackReference
    private Contrat contrat;
}

