package br.com.fiap.gs252soa.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UsuarioRequestDTO(
        @NotBlank String nome,
        @NotBlank @Email String email,
        String areaAtuacao,
        String nivelCarreira
) {}
