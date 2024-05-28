package com.ziedzaafrani.project.facture;

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

import java.util.List;

@RestController
@RequestMapping("factures")
@RequiredArgsConstructor
@Tag(name = "Facture")
public class FactureController {

    private static final Logger logger = LoggerFactory.getLogger(FactureController.class);

    private final FactureService factureService;

    @PostMapping
    public ResponseEntity<FactureResponse> createFacture(@Valid @RequestBody FactureRequest request, Authentication connectedUser) {
        logger.info("Received request to create Facture: {}", request);
        FactureResponse response = factureService.createFacture(request, connectedUser);
        logger.info("Created Facture: {}", response);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FactureResponse> getFactureById(@PathVariable Integer id) {
        logger.info("Received request to get Facture by id: {}", id);
        FactureResponse response = factureService.getFactureById(id);
        logger.info("Fetched Facture: {}", response);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/structure")
    public ResponseEntity<PageResponse<FactureResponse>> getFacturesByStructureId(@RequestParam(defaultValue = "0") int page,
                                                                                  @RequestParam(defaultValue = "10") int size,
                                                                                  Authentication connectedUser) {
        logger.info("Received request to get Factures by structure");
        PageResponse<FactureResponse> responses = factureService.getFacturesByStructureId(page, size, connectedUser);
        logger.info("Fetched Factures: {}", responses);
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/contrat/{contratId}")
    public ResponseEntity<PageResponse<FactureResponse>> getFacturesByContratId(@PathVariable Integer contratId,
                                                                                @RequestParam(defaultValue = "0") int page,
                                                                                @RequestParam(defaultValue = "10") int size) {
        logger.info("Received request to get Factures by contrat id: {}", contratId);
        PageResponse<FactureResponse> responses = factureService.getFacturesByContratId(contratId, page, size);
        logger.info("Fetched Factures: {}", responses);
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/appelOffre/{appelOffreId}")
    public ResponseEntity<PageResponse<FactureResponse>> getFacturesByAppelOffreId(@PathVariable Integer appelOffreId,
                                                                                   @RequestParam(defaultValue = "0") int page,
                                                                                   @RequestParam(defaultValue = "10") int size) {
        logger.info("Received request to get Factures by appelOffre id: {}", appelOffreId);
        PageResponse<FactureResponse> responses = factureService.getFacturesByAppelOffreId(appelOffreId, page, size);
        logger.info("Fetched Factures: {}", responses);
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/projet/{projetId}")
    public ResponseEntity<PageResponse<FactureResponse>> getFacturesByProjetId(@PathVariable Integer projetId,
                                                                               @RequestParam(defaultValue = "0") int page,
                                                                               @RequestParam(defaultValue = "10") int size) {
        logger.info("Received request to get Factures by projet id: {}", projetId);
        PageResponse<FactureResponse> responses = factureService.getFacturesByProjetId(projetId, page, size);
        logger.info("Fetched Factures: {}", responses);
        return ResponseEntity.ok(responses);
    }
}
