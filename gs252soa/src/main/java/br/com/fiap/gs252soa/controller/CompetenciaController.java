package br.com.fiap.gs252soa.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.gs252soa.dtos.CompetenciaRequestDTO;
import br.com.fiap.gs252soa.dtos.CompetenciaResponseDTO;
import br.com.fiap.gs252soa.service.CompetenciaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/competencias")
@RequiredArgsConstructor
public class CompetenciaController {

    private final CompetenciaService competenciaService;

    @PostMapping
    public ResponseEntity<CompetenciaResponseDTO> create(@Valid @RequestBody CompetenciaRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(competenciaService.create(dto));
    }

    @GetMapping
    public ResponseEntity<List<CompetenciaResponseDTO>> getAll() {
        return ResponseEntity.ok(competenciaService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CompetenciaResponseDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(competenciaService.getById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CompetenciaResponseDTO> update(
            @PathVariable Long id,
            @Valid @RequestBody CompetenciaRequestDTO dto
    ) {
        return ResponseEntity.ok(competenciaService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        competenciaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
