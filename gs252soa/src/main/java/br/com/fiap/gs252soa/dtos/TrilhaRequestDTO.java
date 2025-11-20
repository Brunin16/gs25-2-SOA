package br.com.fiap.gs252soa.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record TrilhaRequestDTO(
        @NotBlank String nome,
        String descricao,
        @NotBlank String nivel,
        @NotNull @Positive Integer cargaHoraria,
        String focoPrincipal
) {

}
