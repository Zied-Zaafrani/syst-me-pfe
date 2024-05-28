package com.ziedzaafrani.project.structure;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class StructureMapper {
    private static final Logger logger = LoggerFactory.getLogger(StructureMapper.class);

    public Structure toStructure(StructureRequest request) {
        logger.info("Mapping StructureRequest to Structure: {}", request);
        Structure structure = Structure.builder()
                .id(request.idRequestDto())
                .reference(request.referenceRequestDto())
                .titre(request.titreRequestDto())
                .description(request.descriptionRequestDto())
                .build();
        logger.info("Mapped Structure: {}", structure);
        return structure;
    }

    public StructureResponse toStructureResponse(Structure structure) {
        logger.info("Mapping Structure to StructureResponse: {}", structure);
        StructureResponse structureResponse = StructureResponse.builder()
                .idResponseDto(structure.getId())
                .referenceResponseDto(structure.getReference())
                .titreResponseDto(structure.getTitre())
                .descriptionResponseDto(structure.getDescription())
                .build();
        logger.info("Mapped StructureResponse: {}", structureResponse);
        return structureResponse;
    }

    public void updateStructureFromRequest(Structure structure, StructureRequest request) {
        logger.info("Updating Structure from StructureRequest: {}", request);
        structure.setReference(request.referenceRequestDto());
        structure.setTitre(request.titreRequestDto());
        structure.setDescription(request.descriptionRequestDto());
        logger.info("Updated Structure: {}", structure);
    }
}
