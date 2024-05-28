package com.ziedzaafrani.project.contrat;

import com.ziedzaafrani.project.common.PageResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("contrats")
@RequiredArgsConstructor
@Tag(name = "Contrat")
public class ContratController {

    private static final Logger logger = LoggerFactory.getLogger(ContratController.class);

    private final ContratService contratService;

    @PostMapping
    public ResponseEntity<Integer> createContrat(@Valid @RequestBody ContratRequest request, Authentication connectedUser) {
        logger.info("Received request to create Contrat: {}", request);
        Integer response = contratService.save(request, connectedUser);
        logger.info("Created Contrat with ID: {}", response);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContratResponse> getContratById(@PathVariable Integer id) {
        logger.info("Received request to get Contrat by id: {}", id);
        ContratResponse response = contratService.findContratById(id);
        logger.info("Fetched Contrat: {}", response);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/structure")
    public ResponseEntity<PageResponse<ContratResponse>> getContratsByStructure(
            @RequestParam(name = "page", defaultValue = "0", required = false) int page,
            @RequestParam(name = "size", defaultValue = "10", required = false) int size,
            Authentication connectedUser) {
        logger.info("Received request to get Contrats by structure. Page: {}, Size: {}", page, size);
        PageResponse<ContratResponse> responses = contratService.findAllContratsByUserStructure(page, size, connectedUser);
        logger.info("Fetched Contrats by structure: {}", responses);
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/appelOffre/{appelOffreId}")
    public ResponseEntity<PageResponse<ContratResponse>> getContratsByAppelOffreId(
            @PathVariable Integer appelOffreId,
            @RequestParam(name = "page", defaultValue = "0", required = false) int page,
            @RequestParam(name = "size", defaultValue = "10", required = false) int size) {
        logger.info("Received request to get Contrats by appelOffre id: {}, Page: {}, Size: {}", appelOffreId, page, size);
        PageResponse<ContratResponse> responses = contratService.findAllContratsByAppelOffreId(appelOffreId, page, size);
        logger.info("Fetched Contrats by appelOffre id: {}", responses);
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/projet/{projetId}")
    public ResponseEntity<PageResponse<ContratResponse>> getContratsByProjetId(
            @PathVariable Integer projetId,
            @RequestParam(name = "page", defaultValue = "0", required = false) int page,
            @RequestParam(name = "size", defaultValue = "10", required = false) int size) {
        logger.info("Received request to get Contrats by projet id: {}, Page: {}, Size: {}", projetId, page, size);
        PageResponse<ContratResponse> responses = contratService.findAllContratsByProjetId(projetId, page, size);
        logger.info("Fetched Contrats by projet id: {}", responses);
        return ResponseEntity.ok(responses);
    }

}
