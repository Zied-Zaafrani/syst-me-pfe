package com.ziedzaafrani.project.structure;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.ziedzaafrani.project.common.BaseEntity;
import com.ziedzaafrani.project.projet.Projet;
import com.ziedzaafrani.project.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Entity
@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class Structure extends BaseEntity{

    private String reference;
    private String titre;
    private String description;

    @OneToMany(mappedBy = "structure")
    @JsonManagedReference
    private List<User> users;

    @OneToMany(mappedBy = "structure")
    @JsonManagedReference
    private List<Projet> projects;
}
