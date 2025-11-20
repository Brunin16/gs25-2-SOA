package br.com.fiap.gs252soa.models;

import java.io.Serializable;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class TrilhaCompetenciaId implements Serializable{

    private Long trilhaId;
    private Long competenciaId;
}
