package com.ziedzaafrani.project.structure;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("structures")
@RequiredArgsConstructor
@Tag(name = "Structure")
public class StructureController {

    private final StructureService structureService;

    @PostMapping
    public ResponseEntity<Integer> saveStructure(@Valid @RequestBody StructureRequest request) {
        Integer structureId = structureService.save(request);
        return ResponseEntity.ok(structureId);
    }

    @GetMapping("/{structureId}")
    public ResponseEntity<StructureResponse> findStructureById(@PathVariable("structureId") Integer structureId) {
        StructureResponse response = structureService.findStructureById(structureId);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/all")
    public ResponseEntity<List<StructureResponse>> findAllStructures() {
        List<StructureResponse> responses = structureService.findAllStructures();
        return ResponseEntity.ok(responses);
    }
}
