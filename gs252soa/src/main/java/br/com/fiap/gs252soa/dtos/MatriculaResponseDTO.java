package br.com.fiap.gs252soa.dtos;

import java.time.LocalDate;

import br.com.fiap.gs252soa.models.Matricula;

public record MatriculaResponseDTO(
        Long id,
        Long usuarioId,
        Long trilhaId,
        LocalDate dataInscricao,
        String status
) {
    public static MatriculaResponseDTO fromEntity(Matricula m) {
        return new MatriculaResponseDTO(
                m.getId(),
                m.getUsuario().getId(),
                m.getTrilha().getId(),
                m.getDataInscricao(),
                m.getStatus()
        );
    }
}
