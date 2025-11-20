package br.com.fiap.gs252soa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.fiap.gs252soa.models.Competencia;

@Repository
public interface CompetenciaRepository extends JpaRepository<Competencia, Long> {
}
