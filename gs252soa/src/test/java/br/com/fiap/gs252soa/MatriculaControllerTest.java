package br.com.fiap.gs252soa;

import br.com.fiap.gs252soa.controller.MatriculaController;
import br.com.fiap.gs252soa.dtos.MatriculaResponseDTO;
import br.com.fiap.gs252soa.service.MatriculaService;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(MatriculaController.class)
class MatriculaControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private MatriculaService service;

    @Test
    void deveCriarMatricula() throws Exception {

        MatriculaResponseDTO response = new MatriculaResponseDTO(
                1L, 99L, 100L, LocalDate.now(), "ATIVA"
        );

        when(service.create(any())).thenReturn(response);

        mvc.perform(post("/matriculas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                        {
                            "usuarioId": 99,
                            "trilhaId": 100,
                            "status": "ATIVA"
                        }
                        """))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.usuarioId").value(99));
    }

    @Test
    void deveListarMatriculas() throws Exception {

        when(service.getAll()).thenReturn(
                List.of(
                        new MatriculaResponseDTO(1L, 10L, 20L, LocalDate.now(), "ATIVA"),
                        new MatriculaResponseDTO(2L, 11L, 21L, LocalDate.now(), "CONCLUIDA")
                )
        );

        mvc.perform(get("/matriculas"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[1].status").value("CONCLUIDA"));
    }

    @Test
    void deveAtualizarStatus() throws Exception {

        MatriculaResponseDTO response =
                new MatriculaResponseDTO(1L, 10L, 20L, LocalDate.now(), "CANCELADA");

        when(service.updateStatus(1L, "CANCELADA")).thenReturn(response);

        mvc.perform(patch("/matriculas/1/status?status=CANCELADA"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("CANCELADA"));
    }

    @Test
    void deveDeletarMatricula() throws Exception {

        mvc.perform(delete("/matriculas/1"))
                .andExpect(status().isNoContent());
    }
}
