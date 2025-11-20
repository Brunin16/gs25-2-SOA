package br.com.fiap.gs252soa.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.fiap.gs252soa.dtos.CompetenciaRequestDTO;
import br.com.fiap.gs252soa.dtos.CompetenciaResponseDTO;
import br.com.fiap.gs252soa.exceptions.BadRequestException;
import br.com.fiap.gs252soa.exceptions.ResourceNotFoundException;
import br.com.fiap.gs252soa.models.Competencia;
import br.com.fiap.gs252soa.repository.CompetenciaRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CompetenciaService {

    private final CompetenciaRepository competenciaRepository;

    public CompetenciaResponseDTO create(CompetenciaRequestDTO dto) {

        if (dto.nome() == null || dto.nome().isBlank()) {
            throw new BadRequestException("O nome da competência é obrigatório.");
        }

        Competencia comp = Competencia.builder()
                .nome(dto.nome())
                .categoria(dto.categoria())
                .descricao(dto.descricao())
                .build();

        competenciaRepository.save(comp);

        return CompetenciaResponseDTO.fromEntity(comp);
    }

    public List<CompetenciaResponseDTO> getAll() {
        return competenciaRepository.findAll()
                .stream()
                .map(CompetenciaResponseDTO::fromEntity)
                .toList();
    }

    public CompetenciaResponseDTO getById(Long id) {
        Competencia comp = competenciaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Competência não encontrada"));

        return CompetenciaResponseDTO.fromEntity(comp);
    }

    public CompetenciaResponseDTO update(Long id, CompetenciaRequestDTO dto) {
        Competencia comp = competenciaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Competência não encontrada"));

        if (dto.nome() == null || dto.nome().isBlank()) {
            throw new BadRequestException("O nome da competência é obrigatório.");
        }

        comp.setNome(dto.nome());
        comp.setCategoria(dto.categoria());
        comp.setDescricao(dto.descricao());

        competenciaRepository.save(comp);

        return CompetenciaResponseDTO.fromEntity(comp);
    }

    public void delete(Long id) {
        if (!competenciaRepository.existsById(id)) {
            throw new ResourceNotFoundException("Competência não encontrada");
        }

        competenciaRepository.deleteById(id);
    }
}