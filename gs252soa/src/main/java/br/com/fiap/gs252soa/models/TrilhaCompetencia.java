package br.com.fiap.gs252soa.models;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "trilha_competencia")
public class TrilhaCompetencia {
    @EmbeddedId
    @Builder.Default
    private TrilhaCompetenciaId id = new TrilhaCompetenciaId();

    @ManyToOne
    @MapsId("trilhaId")
    @JoinColumn(name = "trilha_id")
    private Trilha trilha;

    @ManyToOne
    @MapsId("competenciaId")
    @JoinColumn(name = "competencia_id")
    private Competencia competencia;
}
