package br.com.fiap.gs252soa.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.gs252soa.dtos.MatriculaRequestDTO;
import br.com.fiap.gs252soa.dtos.MatriculaResponseDTO;
import br.com.fiap.gs252soa.service.MatriculaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/matriculas")
@RequiredArgsConstructor
public class MatriculaController {

    private final MatriculaService matriculaService;

    @PostMapping
    public ResponseEntity<MatriculaResponseDTO> create(@Valid @RequestBody MatriculaRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(matriculaService.create(dto));
    }

    @GetMapping
    public ResponseEntity<List<MatriculaResponseDTO>> getAll() {
        return ResponseEntity.ok(matriculaService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MatriculaResponseDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(matriculaService.getById(id));
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<MatriculaResponseDTO>> getByUsuario(@PathVariable Long usuarioId) {
        return ResponseEntity.ok(matriculaService.getByUsuario(usuarioId));
    }

    @GetMapping("/trilha/{trilhaId}")
    public ResponseEntity<List<MatriculaResponseDTO>> getByTrilha(@PathVariable Long trilhaId) {
        return ResponseEntity.ok(matriculaService.getByTrilha(trilhaId));
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<MatriculaResponseDTO> updateStatus(
            @PathVariable Long id,
            @RequestParam String status
    ) {
        return ResponseEntity.ok(matriculaService.updateStatus(id, status));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        matriculaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}