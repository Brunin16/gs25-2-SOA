package br.com.fiap.gs252soa.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.fiap.gs252soa.dtos.UsuarioRequestDTO;
import br.com.fiap.gs252soa.dtos.UsuarioResponseDTO;
import br.com.fiap.gs252soa.exceptions.ResourceNotFoundException;
import br.com.fiap.gs252soa.exceptions.BadRequestException;
import br.com.fiap.gs252soa.models.Usuario;
import br.com.fiap.gs252soa.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioResponseDTO create(UsuarioRequestDTO dto) {

        if (usuarioRepository.existsByEmail(dto.email())) {
            throw new BadRequestException("Email já está em uso.");
        }

        Usuario usuario = Usuario.builder()
                .nome(dto.nome())
                .email(dto.email())
                .areaAtuacao(dto.areaAtuacao())
                .nivelCarreira(dto.nivelCarreira())
                .build();

        usuarioRepository.save(usuario);

        return UsuarioResponseDTO.fromEntity(usuario);
    }

    public List<UsuarioResponseDTO> getAll() {
        return usuarioRepository.findAll()
                .stream()
                .map(UsuarioResponseDTO::fromEntity)
                .toList();
    }

    public UsuarioResponseDTO getById(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado"));

        return UsuarioResponseDTO.fromEntity(usuario);
    }

    public UsuarioResponseDTO update(Long id, UsuarioRequestDTO dto) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado"));

        if (!usuario.getEmail().equals(dto.email()) &&
                usuarioRepository.existsByEmail(dto.email())) {
            throw new BadRequestException("Email já está em uso.");
        }

        usuario.setNome(dto.nome());
        usuario.setEmail(dto.email());
        usuario.setAreaAtuacao(dto.areaAtuacao());
        usuario.setNivelCarreira(dto.nivelCarreira());

        usuarioRepository.save(usuario);
        return UsuarioResponseDTO.fromEntity(usuario);
    }

    public void delete(Long id) {
        if (!usuarioRepository.existsById(id)) {
            throw new ResourceNotFoundException("Usuário não encontrado");
        }
        usuarioRepository.deleteById(id);
    }

}
