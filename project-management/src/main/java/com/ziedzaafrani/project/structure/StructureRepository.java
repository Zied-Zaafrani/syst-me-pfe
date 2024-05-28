package com.ziedzaafrani.project.structure;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface StructureRepository extends JpaRepository<Structure, Integer>, JpaSpecificationExecutor<Structure> {
    boolean existsByTitre(String titre);
}
