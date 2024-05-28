package com.ziedzaafrani.project.projet;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.ziedzaafrani.project.appel_offre.AppelOffre;
import com.ziedzaafrani.project.common.BaseEntity;
import com.ziedzaafrani.project.structure.Structure;
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
public class Projet extends BaseEntity {

    private String reference;
    private String titre;

    @Column(columnDefinition = "TEXT")
    private String description;
    private double cout;
    private LocalDate startDate;
    private LocalDate endDate;

    @ManyToOne
    @JoinColumn(name = "structure_id", nullable = false)
    @JsonBackReference
    private Structure structure;

    @OneToMany(mappedBy = "projet")
    @JsonManagedReference
    private List<AppelOffre> appelOffres;

}