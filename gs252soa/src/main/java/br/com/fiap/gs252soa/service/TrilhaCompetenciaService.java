package br.com.fiap.gs252soa.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.fiap.gs252soa.dtos.TrilhaCompetenciaRequestDTO;
import br.com.fiap.gs252soa.dtos.TrilhaCompetenciaResponseDTO;
import br.com.fiap.gs252soa.exceptions.BadRequestException;
import br.com.fiap.gs252soa.exceptions.ResourceNotFoundException;
import br.com.fiap.gs252soa.models.Competencia;
import br.com.fiap.gs252soa.models.Trilha;
import br.com.fiap.gs252soa.models.TrilhaCompetencia;
import br.com.fiap.gs252soa.models.TrilhaCompetenciaId;
import br.com.fiap.gs252soa.repository.CompetenciaRepository;
import br.com.fiap.gs252soa.repository.TrilhaCompetenciaRepository;
import br.com.fiap.gs252soa.repository.TrilhaRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TrilhaCompetenciaService {

    private final TrilhaCompetenciaRepository trilhaCompetenciaRepository;
    private final TrilhaRepository trilhaRepository;
    private final CompetenciaRepository competenciaRepository;

    public TrilhaCompetenciaResponseDTO addCompetencia(TrilhaCompetenciaRequestDTO dto) {

        Long trilhaId = dto.trilhaId();
        Long competenciaId = dto.competenciaId();

        Trilha trilha = trilhaRepository.findById(trilhaId)
                .orElseThrow(() -> new ResourceNotFoundException("Trilha não encontrada"));

        Competencia competencia = competenciaRepository.findById(competenciaId)
                .orElseThrow(() -> new ResourceNotFoundException("Competência não encontrada"));

        if (trilhaCompetenciaRepository.existsByIdTrilhaIdAndIdCompetenciaId(trilhaId, competenciaId)) {
            throw new BadRequestException("Esta competência já está vinculada a esta trilha.");
        }

        TrilhaCompetencia tc = TrilhaCompetencia.builder()
                .id(new TrilhaCompetenciaId(trilhaId, competenciaId))
                .trilha(trilha)
                .competencia(competencia)
                .build();

        trilhaCompetenciaRepository.save(tc);

        return TrilhaCompetenciaResponseDTO.fromEntity(tc);
    }

    public List<TrilhaCompetenciaResponseDTO> getCompetenciasFromTrilha(Long trilhaId) {

        if (!trilhaRepository.existsById(trilhaId)) {
            throw new ResourceNotFoundException("Trilha não encontrada");
        }

        return trilhaCompetenciaRepository.findByIdTrilhaId(trilhaId)
                .stream()
                .map(TrilhaCompetenciaResponseDTO::fromEntity)
                .toList();
    }

    public List<TrilhaCompetenciaResponseDTO> getTrilhasFromCompetencia(Long competenciaId) {

        if (!competenciaRepository.existsById(competenciaId)) {
            throw new ResourceNotFoundException("Competência não encontrada");
        }

        return trilhaCompetenciaRepository.findByIdCompetenciaId(competenciaId)
                .stream()
                .map(TrilhaCompetenciaResponseDTO::fromEntity)
                .toList();
    }

    public void remove(Long trilhaId, Long competenciaId) {

        TrilhaCompetenciaId id = new TrilhaCompetenciaId(trilhaId, competenciaId);

        if (!trilhaCompetenciaRepository.existsById(id)) {
            throw new ResourceNotFoundException("Esta competência não está vinculada a esta trilha.");
        }

        trilhaCompetenciaRepository.deleteById(id);
    }
}