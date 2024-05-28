package com.ziedzaafrani.project.contrat;

import com.ziedzaafrani.project.appel_offre.AppelOffre;
import com.ziedzaafrani.project.projet.Projet;
import com.ziedzaafrani.project.structure.Structure;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;


public interface ContratRepository extends JpaRepository<Contrat, Integer>, JpaSpecificationExecutor<Contrat> {

    boolean existsByTitreAndAppelOffre(String titre, AppelOffre appelOffre);

    @Query("SELECT c FROM Contrat c WHERE c.appelOffre.projet.structure = :structure")
    Page<Contrat> findByStructure(Structure structure, Pageable pageable);

    @Query("SELECT c FROM Contrat c WHERE c.appelOffre = :appelOffre")
    Page<Contrat> findByAppelOffre(AppelOffre appelOffre, Pageable pageable);

    @Query("SELECT c FROM Contrat c WHERE c.appelOffre.projet = :projet")
    Page<Contrat> findByProjet(Projet projet, Pageable pageable);

    Optional<Contrat> findById(Integer contratId);
}
