package com.ziedzaafrani.project.projet;

import com.ziedzaafrani.project.structure.Structure;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ProjetRepository extends JpaRepository<Projet, Integer>, JpaSpecificationExecutor<Projet> {

    @Query("SELECT p FROM Projet p WHERE p.structure = :structure")
    Page<Projet> findByStructure(Structure structure, Pageable pageable);

    boolean existsByTitreAndStructure(String titre, Structure structure);
}
