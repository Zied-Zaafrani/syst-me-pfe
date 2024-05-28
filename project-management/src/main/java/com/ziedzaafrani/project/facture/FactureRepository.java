package com.ziedzaafrani.project.facture;

import com.ziedzaafrani.project.appel_offre.AppelOffre;
import com.ziedzaafrani.project.contrat.Contrat;
import com.ziedzaafrani.project.projet.Projet;
import com.ziedzaafrani.project.structure.Structure;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FactureRepository extends JpaRepository<Facture, Integer>, JpaSpecificationExecutor<Facture> {

    boolean existsByTitreAndContrat(String titre, Contrat contrat);

    @Query("SELECT f FROM Facture f WHERE f.contrat.appelOffre.projet.structure = :structure")
    Page<Facture> findByStructure(Structure structure, Pageable pageable);

    @Query("SELECT f FROM Facture f WHERE f.contrat= :contrat")
    Page<Facture>   findByContrat(Contrat contrat, Pageable pageable);

    @Query("SELECT f FROM Facture f WHERE f.contrat.appelOffre = :appelOffre")
    Page<Facture> findByAppelOffre(AppelOffre appelOffre, Pageable pageable);

    @Query("SELECT f FROM Facture f WHERE f.contrat.appelOffre.projet = :projet")
    Page<Facture> findByProjet(Projet projet, Pageable pageable);
}
