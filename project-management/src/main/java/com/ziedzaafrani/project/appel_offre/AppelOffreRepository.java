package com.ziedzaafrani.project.appel_offre;

import com.ziedzaafrani.project.projet.Projet;
import com.ziedzaafrani.project.structure.Structure;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AppelOffreRepository extends JpaRepository<AppelOffre, Integer>, JpaSpecificationExecutor<AppelOffre> {

    boolean existsByTitreAndProjet(String titre, Projet projet);

    @Query("SELECT a FROM AppelOffre a WHERE a.projet.structure = :structure")
    Page<AppelOffre> findByStructure(Structure structure, Pageable pageable);

    @Query("SELECT a FROM AppelOffre a WHERE a.projet = :projet")
    Page<AppelOffre> findByProjet(Projet projet, Pageable pageable);

    Optional<AppelOffre> findById(Integer appelOffreId);


}
