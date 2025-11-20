package br.com.fiap.gs252soa.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.gs252soa.dtos.TrilhaCompetenciaRequestDTO;
import br.com.fiap.gs252soa.dtos.TrilhaCompetenciaResponseDTO;
import br.com.fiap.gs252soa.service.TrilhaCompetenciaService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Tag(name = "Trilhas")
@RestController
@RequestMapping("/trilhas")
@RequiredArgsConstructor
public class TrilhaCompetenciaController {

    private final TrilhaCompetenciaService service;

    @PostMapping("/{trilhaId}/competencias")
    public ResponseEntity<TrilhaCompetenciaResponseDTO> addCompetencia(
            @PathVariable Long trilhaId,
            @Valid @RequestBody TrilhaCompetenciaRequestDTO dto
    ) {
        // Garante que o DTO use trilhaId consistente
        dto = new TrilhaCompetenciaRequestDTO(trilhaId, dto.competenciaId());
        return ResponseEntity.status(HttpStatus.CREATED).body(service.addCompetencia(dto));
    }

    @GetMapping("/{trilhaId}/competencias")
    public ResponseEntity<List<TrilhaCompetenciaResponseDTO>> listCompetencias(@PathVariable Long trilhaId) {
        return ResponseEntity.ok(service.getCompetenciasFromTrilha(trilhaId));
    }

    @DeleteMapping("/{trilhaId}/competencias/{competenciaId}")
    public ResponseEntity<Void> remove(
            @PathVariable Long trilhaId,
            @PathVariable Long competenciaId
    ) {
        service.remove(trilhaId, competenciaId);
        return ResponseEntity.noContent().build();
    }
}
