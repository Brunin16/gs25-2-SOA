package br.com.fiap.gs252soa.dtos;

import br.com.fiap.gs252soa.models.Trilha;

public record TrilhaResponseDTO(
        Long id,
        String nome,
        String descricao,
        String nivel,
        Integer cargaHoraria,
        String focoPrincipal
) {
    public static TrilhaResponseDTO fromEntity(Trilha t) {
        return new TrilhaResponseDTO(
                t.getId(),
                t.getNome(),
                t.getDescricao(),
                t.getNivel(),
                t.getCargaHoraria(),
                t.getFocoPrincipal()
        );
    }
}