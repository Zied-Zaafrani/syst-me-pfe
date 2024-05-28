package com.ziedzaafrani.project.appel_offre;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ziedzaafrani.project.common.PageResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("appels-offres")
@RequiredArgsConstructor
@Tag(name = "Appel Offre")
public class AppelOffreController {

    private static final Logger logger = LoggerFactory.getLogger(AppelOffreController.class);

    private final AppelOffreService service;

    @PostMapping
    public ResponseEntity<Integer> saveAppelOffre(
            @Valid @RequestBody AppelOffreRequest request,
            Authentication connectedUser
    ) {
        logger.info("Received request to save an AppelOffre.");
        logger.debug("Request body: {}", request);
        ResponseEntity<Integer> response = ResponseEntity.ok(service.save(request, connectedUser));
        logger.info("AppelOffre saved successfully with ID: {}", response.getBody());
        return response;
    }

    @GetMapping("/{appelOffreId}")
    public ResponseEntity<AppelOffreResponse> findAppelOffreById(
            @PathVariable("appelOffreId") Integer appelOffreId
    ) {
        logger.info("Received request to find an AppelOffre by ID: {}", appelOffreId);
        ResponseEntity<AppelOffreResponse> response = ResponseEntity.ok(service.findAppelOffreById(appelOffreId));
        logger.info("AppelOffre found successfully.");
        return response;
    }

    @GetMapping
    public ResponseEntity<PageResponse<AppelOffreResponse>> findAllAppelOffresByUserStructure(
            @RequestParam(name = "page", defaultValue = "0", required = false) int page,
            @RequestParam(name = "size", defaultValue = "10", required = false) int size,
            Authentication connectedUser
    ) {
        logger.info("Received request to find all AppelOffres by user's structure. Page: {}, Size: {}", page, size);
        PageResponse<AppelOffreResponse> appelOffres = service.findAllAppelOffresByUserStructure(page, size, connectedUser);
        logger.info("All AppelOffres retrieved successfully.");
        return ResponseEntity.ok(appelOffres);
    }

    @GetMapping("/byProjet/{projetId}")
    public ResponseEntity<PageResponse<AppelOffreResponse>> findAllAppelOffresByProjetId(
            @PathVariable("projetId") Integer projetId,
            @RequestParam(name = "page", defaultValue = "0", required = false) int page,
            @RequestParam(name = "size", defaultValue = "10", required = false) int size

    ) {
        logger.info("Received request to find all AppelOffres by project ID: {}", projetId);
        PageResponse<AppelOffreResponse> appelOffres = service.findAllAppelOffresByProjetId(projetId, page, size);
        logger.info("All AppelOffres retrieved successfully.");
        return ResponseEntity.ok(appelOffres);
    }


    @GetMapping("/all")
    public ResponseEntity<PageResponse<AppelOffreResponse>> findAllAppelOffres(
            @RequestParam(name = "page", defaultValue = "0", required = false) int page,
            @RequestParam(name = "size", defaultValue = "10", required = false) int size
    ) {
        logger.info("Received request to find all AppelOffres.");
        PageResponse<AppelOffreResponse> appelOffres = service.findAllAppelOffres(page, size);
        logger.info("All AppelOffres retrieved successfully.");
        return ResponseEntity.ok(appelOffres);
    }

    @DeleteMapping("/{appelOffreId}")
    public ResponseEntity<Void> deleteAppelOffre(
            @PathVariable("appelOffreId") Integer appelOffreId
    ) {
        logger.info("Received request to delete AppelOffre with ID: {}", appelOffreId);
        service.delete(appelOffreId);
        logger.info("AppelOffre deleted successfully.");
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{appelOffreId}/{status}")
    public ResponseEntity<Void> updateStatus(
            @PathVariable("appelOffreId") Integer appelOffreId,
            @PathVariable("status") String status
    ) {
        logger.info("Received request to update status of AppelOffre with ID: {} with status : {}", appelOffreId, status);
        service.updateStatus(appelOffreId, status);
        logger.info("AppelOffre status updated successfully.");
        return ResponseEntity.noContent().build();
    }
}



