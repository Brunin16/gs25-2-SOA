package br.com.fiap.gs252soa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.fiap.gs252soa.models.TrilhaCompetencia;
import br.com.fiap.gs252soa.models.TrilhaCompetenciaId;

@Repository
public interface TrilhaCompetenciaRepository extends JpaRepository<TrilhaCompetencia, TrilhaCompetenciaId> {
    List<TrilhaCompetencia> findByIdTrilhaId(Long trilhaId);
    List<TrilhaCompetencia> findByIdCompetenciaId(Long competenciaId);
    boolean existsByIdTrilhaIdAndIdCompetenciaId(Long trilhaId, Long competenciaId);
}
