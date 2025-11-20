package br.com.fiap.gs252soa;

import br.com.fiap.gs252soa.controller.TrilhaCompetenciaController;
import br.com.fiap.gs252soa.dtos.TrilhaCompetenciaResponseDTO;
import br.com.fiap.gs252soa.service.TrilhaCompetenciaService;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(TrilhaCompetenciaController.class)
class TrilhaCompetenciaControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private TrilhaCompetenciaService service;

    @Test
    void deveAdicionarCompetenciaNaTrilha() throws Exception {

        var response = new TrilhaCompetenciaResponseDTO(1L, 2L);

        when(service.addCompetencia(any())).thenReturn(response);

        mvc.perform(post("/trilhas/1/competencias")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                        {
                            "trilhaId": 999,
                            "competenciaId": 2
                        }
                        """))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.trilhaId").value(1L))
                .andExpect(jsonPath("$.competenciaId").value(2L));
    }

    @Test
    void deveListarCompetencias() throws Exception {

        when(service.getCompetenciasFromTrilha(1L)).thenReturn(
                java.util.List.of(
                        new TrilhaCompetenciaResponseDTO(1L, 2L),
                        new TrilhaCompetenciaResponseDTO(1L, 3L)
                )
        );

        mvc.perform(get("/trilhas/1/competencias"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[1].competenciaId").value(3));
    }

    @Test
    void deveRemoverCompetencia() throws Exception {

        mvc.perform(delete("/trilhas/1/competencias/2"))
                .andExpect(status().isNoContent());
    }
}
