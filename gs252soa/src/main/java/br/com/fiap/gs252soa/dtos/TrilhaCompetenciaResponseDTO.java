package br.com.fiap.gs252soa.dtos;

import br.com.fiap.gs252soa.models.TrilhaCompetencia;

public record TrilhaCompetenciaResponseDTO(
        Long trilhaId,
        Long competenciaId
) {
    public static TrilhaCompetenciaResponseDTO fromEntity(TrilhaCompetencia tc) {
        return new TrilhaCompetenciaResponseDTO(
                tc.getTrilha().getId(),
                tc.getCompetencia().getId()
        );
    }
}
