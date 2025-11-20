package br.com.fiap.gs252soa.dtos;

import jakarta.validation.constraints.NotBlank;

public record CompetenciaRequestDTO(
        @NotBlank String nome,
        String categoria,
        String descricao
) {}