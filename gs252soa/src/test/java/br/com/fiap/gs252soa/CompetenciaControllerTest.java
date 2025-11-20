package br.com.fiap.gs252soa;

import br.com.fiap.gs252soa.controller.CompetenciaController;
import br.com.fiap.gs252soa.dtos.CompetenciaResponseDTO;
import br.com.fiap.gs252soa.service.CompetenciaService;

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

@WebMvcTest(CompetenciaController.class)
class CompetenciaControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private CompetenciaService service;

    @Test
    void deveCriarCompetencia() throws Exception {

        CompetenciaResponseDTO response =
                new CompetenciaResponseDTO(1L, "Java", "Backend", "Descrição");

        when(service.create(any())).thenReturn(response);

        mvc.perform(post("/competencias")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                        {
                            "nome": "Java",
                            "categoria": "Backend",
                            "descricao": "Descrição"
                        }
                        """))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nome").value("Java"));
    }

    @Test
    void deveListarCompetencias() throws Exception {

        when(service.getAll()).thenReturn(
                java.util.List.of(
                        new CompetenciaResponseDTO(1L, "Java", "Backend", "Desc"),
                        new CompetenciaResponseDTO(2L, "SQL", "DB", "Desc")
                )
        );

        mvc.perform(get("/competencias"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[1].nome").value("SQL"));
    }

    @Test
    void deveBuscarPorId() throws Exception {

        when(service.getById(1L)).thenReturn(
                new CompetenciaResponseDTO(1L, "Java", "Backend", "Desc")
        );

        mvc.perform(get("/competencias/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("Java"));
    }

    @Test
    void deveDeletarCompetencia() throws Exception {
        mvc.perform(delete("/competencias/1"))
                .andExpect(status().isNoContent());
    }
}
