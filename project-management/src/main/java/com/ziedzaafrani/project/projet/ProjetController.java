package com.ziedzaafrani.project.projet;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.ziedzaafrani.project.common.PageResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static jakarta.mail.event.FolderEvent.CREATED;

@RestController
@RequestMapping("projets")
@RequiredArgsConstructor
@Tag(name = "Projet")
public class ProjetController {

    private static final Logger logger = LogManager.getLogger(ProjetController.class);

    private final ProjetService service;

    @PostMapping 
    public ResponseEntity<Integer> saveProjet(
            @Valid @RequestBody ProjetRequest request,
            Authentication connectedUser
    ) {
        logger.info("Received request to save a project.");
        logger.debug("Request body: {}", request);
        ResponseEntity<Integer> response = ResponseEntity.ok(service.save(request, connectedUser));
        logger.info("Project saved successfully with ID: {}", response.getBody());
        return response;
    }

    @GetMapping("/{projet-id}")
    public ResponseEntity<ProjetResponse> findProjetById(
            @PathVariable("projet-id") Integer projetId
    ) {
        
        logger.info("Received request to find a project by ID: {}", projetId);
        ResponseEntity<ProjetResponse> response = ResponseEntity.ok(service.findProjetById(projetId));
        logger.info("Project found successfully.");
        return response;
    }

    @GetMapping
    public ResponseEntity<PageResponse<ProjetResponse>> findAllProjetsByUserStructure(
            @RequestParam(name = "page", defaultValue = "0", required = false) int page,
            @RequestParam(name = "size", defaultValue = "10", required = false) int size,
            Authentication connectedUser
    ) {
        logger.info("Received request to find all projects by structure. Page: {}, Size: {}", page, size);
        PageResponse<ProjetResponse> projets = service.findAllProjetsByUserStructure(page, size, connectedUser);
        logger.info("All projects retrieved successfully.");
        return ResponseEntity.ok(projets);
    }

    @GetMapping("/all")
    public ResponseEntity<PageResponse<ProjetResponse>> findAllProjets(
            @RequestParam(name = "page", defaultValue = "0", required = false) int page,
            @RequestParam(name = "size", defaultValue = "10", required = false) int size
    ) {
        logger.info("Received request to find all projects.");
        PageResponse<ProjetResponse> projets = service.findAllProjets(page, size);
        logger.info("All projects retrieved successfully.");
        return ResponseEntity.ok(projets);
    }

    @DeleteMapping("/{projetId}")
    public ResponseEntity<Void> deleteProjet(
            @PathVariable("projetId") Integer projetId
    ) {
        logger.info("Received request to delete project with ID: {}", projetId);
        service.delete(projetId);
        logger.info("Project deleted successfully.");
        return ResponseEntity.noContent().build();
    }

}
