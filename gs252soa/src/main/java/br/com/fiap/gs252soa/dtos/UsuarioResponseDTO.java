package br.com.fiap.gs252soa.dtos;

import br.com.fiap.gs252soa.models.Usuario;

public record UsuarioResponseDTO(
        Long id,
        String nome,
        String email,
        String areaAtuacao,
        String nivelCarreira
) {
    public static UsuarioResponseDTO fromEntity(Usuario u) {
        return new UsuarioResponseDTO(
                u.getId(),
                u.getNome(),
                u.getEmail(),
                u.getAreaAtuacao(),
                u.getNivelCarreira()
        );
    }
}
