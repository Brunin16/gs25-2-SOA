package br.com.fiap.gs252soa.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.fiap.gs252soa.dtos.MatriculaRequestDTO;
import br.com.fiap.gs252soa.dtos.MatriculaResponseDTO;
import br.com.fiap.gs252soa.exceptions.BadRequestException;
import br.com.fiap.gs252soa.exceptions.ResourceNotFoundException;
import br.com.fiap.gs252soa.models.Matricula;
import br.com.fiap.gs252soa.models.Trilha;
import br.com.fiap.gs252soa.models.Usuario;
import br.com.fiap.gs252soa.repository.MatriculaRepository;
import br.com.fiap.gs252soa.repository.TrilhaRepository;
import br.com.fiap.gs252soa.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MatriculaService {

    private final MatriculaRepository matriculaRepository;
    private final UsuarioRepository usuarioRepository;
    private final TrilhaRepository trilhaRepository;

    public MatriculaResponseDTO create(MatriculaRequestDTO dto) {

        Long usuarioId = dto.usuarioId();
        Long trilhaId = dto.trilhaId();

        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado"));

        Trilha trilha = trilhaRepository.findById(trilhaId)
                .orElseThrow(() -> new ResourceNotFoundException("Trilha não encontrada"));

        if (matriculaRepository.existsByUsuarioIdAndTrilhaId(usuarioId, trilhaId)) {
            throw new BadRequestException("Usuário já matriculado nesta trilha.");
        }

        Matricula matricula = Matricula.builder()
                .usuario(usuario)
                .trilha(trilha)
                .status(dto.status())
                .build();

        matriculaRepository.save(matricula);

        return MatriculaResponseDTO.fromEntity(matricula);
    }

    public List<MatriculaResponseDTO> getAll() {
        return matriculaRepository.findAll()
                .stream()
                .map(MatriculaResponseDTO::fromEntity)
                .toList();
    }

    public MatriculaResponseDTO getById(Long id) {
        Matricula m = matriculaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Matrícula não encontrada"));

        return MatriculaResponseDTO.fromEntity(m);
    }

    public List<MatriculaResponseDTO> getByUsuario(Long usuarioId) {

        if (!usuarioRepository.existsById(usuarioId)) {
            throw new ResourceNotFoundException("Usuário não encontrado");
        }

        return matriculaRepository.findByUsuarioId(usuarioId)
                .stream()
                .map(MatriculaResponseDTO::fromEntity)
                .toList();
    }

    public List<MatriculaResponseDTO> getByTrilha(Long trilhaId) {

        if (!trilhaRepository.existsById(trilhaId)) {
            throw new ResourceNotFoundException("Trilha não encontrada");
        }

        return matriculaRepository.findByTrilhaId(trilhaId)
                .stream()
                .map(MatriculaResponseDTO::fromEntity)
                .toList();
    }

    public MatriculaResponseDTO updateStatus(Long id, String novoStatus) {

        Matricula matricula = matriculaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Matrícula não encontrada"));

        if (novoStatus == null || novoStatus.isBlank()) {
            throw new BadRequestException("Status inválido.");
        }

        matricula.setStatus(novoStatus);

        matriculaRepository.save(matricula);

        return MatriculaResponseDTO.fromEntity(matricula);
    }

    public void delete(Long id) {
        if (!matriculaRepository.existsById(id)) {
            throw new ResourceNotFoundException("Matrícula não encontrada");
        }

        matriculaRepository.deleteById(id);
    }
}
