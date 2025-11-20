package br.com.fiap.gs252soa.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.fiap.gs252soa.dtos.TrilhaRequestDTO;
import br.com.fiap.gs252soa.dtos.TrilhaResponseDTO;
import br.com.fiap.gs252soa.exceptions.BadRequestException;
import br.com.fiap.gs252soa.exceptions.ResourceNotFoundException;
import br.com.fiap.gs252soa.models.Trilha;
import br.com.fiap.gs252soa.repository.TrilhaRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TrilhaService {

    private final TrilhaRepository trilhaRepository;

    public TrilhaResponseDTO create(TrilhaRequestDTO dto) {

        if (dto.cargaHoraria() <= 0) {
            throw new BadRequestException("Carga horária deve ser maior que zero.");
        }

        Trilha trilha = Trilha.builder()
                .nome(dto.nome())
                .descricao(dto.descricao())
                .nivel(dto.nivel())
                .cargaHoraria(dto.cargaHoraria())
                .focoPrincipal(dto.focoPrincipal())
                .build();

        trilhaRepository.save(trilha);

        return TrilhaResponseDTO.fromEntity(trilha);
    }

    public List<TrilhaResponseDTO> getAll() {
        return trilhaRepository.findAll()
                .stream()
                .map(TrilhaResponseDTO::fromEntity)
                .toList();
    }

    public TrilhaResponseDTO getById(Long id) {
        Trilha trilha = trilhaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Trilha não encontrada"));

        return TrilhaResponseDTO.fromEntity(trilha);
    }

    public TrilhaResponseDTO update(Long id, TrilhaRequestDTO dto) {
        Trilha trilha = trilhaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Trilha não encontrada"));

        if (dto.cargaHoraria() <= 0) {
            throw new BadRequestException("Carga horária deve ser maior que zero.");
        }

        trilha.setNome(dto.nome());
        trilha.setDescricao(dto.descricao());
        trilha.setNivel(dto.nivel());
        trilha.setCargaHoraria(dto.cargaHoraria());
        trilha.setFocoPrincipal(dto.focoPrincipal());

        trilhaRepository.save(trilha);

        return TrilhaResponseDTO.fromEntity(trilha);
    }

    public void delete(Long id) {
        if (!trilhaRepository.existsById(id)) {
            throw new ResourceNotFoundException("Trilha não encontrada");
        }

        trilhaRepository.deleteById(id);
    }
}
