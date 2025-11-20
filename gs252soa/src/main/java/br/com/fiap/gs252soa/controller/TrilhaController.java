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

import br.com.fiap.gs252soa.dtos.TrilhaRequestDTO;
import br.com.fiap.gs252soa.dtos.TrilhaResponseDTO;
import br.com.fiap.gs252soa.service.TrilhaService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Tag(name = "Trilhas")
@RestController
@RequestMapping("/trilhas")
@RequiredArgsConstructor
public class TrilhaController {


    private final TrilhaService trilhaService;

    @PostMapping
    public ResponseEntity<TrilhaResponseDTO> create(@Valid @RequestBody TrilhaRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(trilhaService.create(dto));
    }

    @GetMapping
    public ResponseEntity<List<TrilhaResponseDTO>> getAll() {
        return ResponseEntity.ok(trilhaService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TrilhaResponseDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(trilhaService.getById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TrilhaResponseDTO> update(
            @PathVariable Long id,
            @Valid @RequestBody TrilhaRequestDTO dto
    ) {
        return ResponseEntity.ok(trilhaService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        trilhaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
