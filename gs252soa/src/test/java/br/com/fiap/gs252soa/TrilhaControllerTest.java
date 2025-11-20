package br.com.fiap.gs252soa;

import br.com.fiap.gs252soa.controller.TrilhaController;
import br.com.fiap.gs252soa.dtos.TrilhaResponseDTO;
import br.com.fiap.gs252soa.service.TrilhaService;

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

@WebMvcTest(TrilhaController.class)
class TrilhaControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private TrilhaService service;

    @Test
    void deveCriarTrilha() throws Exception {

        TrilhaResponseDTO response =
                new TrilhaResponseDTO(1L, "Back-end", "Curso", "Intermediario", 80, "Java");

        when(service.create(any())).thenReturn(response);

        mvc.perform(post("/trilhas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                        {
                            "nome": "Back-end",
                            "descricao": "Curso",
                            "nivel": "Intermediario",
                            "cargaHoraria": 80,
                            "focoPrincipal": "Java"
                        }
                        """))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nome").value("Back-end"));
    }

    @Test
    void deveListarTrilhas() throws Exception {

        when(service.getAll()).thenReturn(
                java.util.List.of(
                        new TrilhaResponseDTO(1L, "Web", "Desc", "Jr", 40, "HTML"),
                        new TrilhaResponseDTO(2L, "Back", "Desc", "Pleno", 80, "Java")
                )
        );

        mvc.perform(get("/trilhas"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[1].focoPrincipal").value("Java"));
    }

    @Test
    void deveBuscarPorId() throws Exception {

        when(service.getById(1L)).thenReturn(
                new TrilhaResponseDTO(1L, "Back-end", "Curso", "Pleno", 80, "Java")
        );

        mvc.perform(get("/trilhas/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("Back-end"));
    }

    @Test
    void deveDeletarTrilha() throws Exception {
        mvc.perform(delete("/trilhas/1"))
                .andExpect(status().isNoContent());
    }
}
