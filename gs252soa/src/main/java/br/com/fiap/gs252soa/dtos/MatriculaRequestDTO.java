package br.com.fiap.gs252soa.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record MatriculaRequestDTO(
        @NotNull Long usuarioId,
        @NotNull Long trilhaId,
        @NotBlank String status
) {}
