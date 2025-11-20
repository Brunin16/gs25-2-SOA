package br.com.fiap.gs252soa.dtos;

import br.com.fiap.gs252soa.models.Competencia;

public record CompetenciaResponseDTO(
        Long id,
        String nome,
        String categoria,
        String descricao
) {
    public static CompetenciaResponseDTO fromEntity(Competencia c) {
        return new CompetenciaResponseDTO(
                c.getId(),
                c.getNome(),
                c.getCategoria(),
                c.getDescricao()
        );
    }
}