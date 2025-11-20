package br.com.fiap.gs252soa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.fiap.gs252soa.models.Matricula;

@Repository
public interface MatriculaRepository extends JpaRepository<Matricula, Long> {
    List<Matricula> findByUsuarioId(Long usuarioId);
    List<Matricula> findByTrilhaId(Long trilhaId);
    boolean existsByUsuarioIdAndTrilhaId(Long usuarioId, Long trilhaId);
}
