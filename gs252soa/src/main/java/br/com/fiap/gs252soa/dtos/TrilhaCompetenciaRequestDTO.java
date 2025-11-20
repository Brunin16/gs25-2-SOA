package br.com.fiap.gs252soa.dtos;

import jakarta.validation.constraints.NotNull;

public record TrilhaCompetenciaRequestDTO(
        @NotNull Long trilhaId,
        @NotNull Long competenciaId
) {}
