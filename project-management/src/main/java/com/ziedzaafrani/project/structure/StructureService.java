package com.ziedzaafrani.project.structure;

import com.ziedzaafrani.project.projet.Projet;
import com.ziedzaafrani.project.projet.ProjetRepository;
import com.ziedzaafrani.project.projet.ProjetResponse;
import com.ziedzaafrani.project.user.User;
import com.ziedzaafrani.project.user.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StructureService {

    private final StructureRepository structureRepository;
    private final StructureMapper structureMapper;

    public Integer save(StructureRequest request) {

        if (structureRepository.existsByTitre(request.titreRequestDto())) {
            throw new IllegalArgumentException("A structure with the same titre already exists");
        }

        Structure structure = structureMapper.toStructure(request);
        structure.setReference(generateRandomReference());
        Structure savedStructure = structureRepository.save(structure);
        return savedStructure.getId();
    }

    private String generateRandomReference() {
        return RandomStringUtils.randomAlphanumeric(8);
    }

    public StructureResponse findStructureById(Integer structureId) {
        Structure structure = structureRepository.findById(structureId)
                .orElseThrow(() -> new EntityNotFoundException("No Structure found with ID: " + structureId));
        return structureMapper.toStructureResponse(structure);
    }

    public List<StructureResponse> findAllStructures() {
        List<Structure> structures = structureRepository.findAll();
        return structures.stream()
                .map(structureMapper::toStructureResponse)
                .collect(Collectors.toList());
    }
}

