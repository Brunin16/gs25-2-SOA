package br.com.fiap.gs252soa;

import br.com.fiap.gs252soa.controller.UsuarioController;
import br.com.fiap.gs252soa.dtos.UsuarioResponseDTO;
import br.com.fiap.gs252soa.service.UsuarioService;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;

import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UsuarioController.class)
class UsuarioControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private UsuarioService service;

    @Test
    void deveCriarUsuario() throws Exception {

        UsuarioResponseDTO response = new UsuarioResponseDTO(
                1L,
                "Bruno",
                "bruno@gmail.com",
                "Dev",
                "Pleno"
        );

        when(service.create(any())).thenReturn(response);

        mvc.perform(post("/usuarios")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                            {
                                "nome": "Bruno",
                                "email": "bruno@gmail.com",
                                "areaAtuacao": "Dev",
                                "nivelCarreira": "Pleno"
                            }
                        """))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.nome").value("Bruno"))
                .andExpect(jsonPath("$.email").value("bruno@gmail.com"));
    }

    @Test
    void deveListarUsuarios() throws Exception {

        when(service.getAll()).thenReturn(
                java.util.List.of(
                        new UsuarioResponseDTO(1L, "A", "a@a.com", "TI", "Jr"),
                        new UsuarioResponseDTO(2L, "B", "b@b.com", "TI", "Pleno")
                )
        );

        mvc.perform(get("/usuarios"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[1].nome").value("B"));
    }

    @Test
    void deveBuscarPorId() throws Exception {

        when(service.getById(1L))
                .thenReturn(new UsuarioResponseDTO(1L, "Bruno", "b@b.com", null, null));

        mvc.perform(get("/usuarios/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("Bruno"));
    }

    @Test
    void deveAtualizarUsuario() throws Exception {

        UsuarioResponseDTO response =
                new UsuarioResponseDTO(1L, "Bruno Atual", "b@b.com", "Dev", "Senior");

        when(service.update(Mockito.eq(1L), any())).thenReturn(response);

        mvc.perform(put("/usuarios/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                            {
                                "nome": "Bruno Atual",
                                "email": "b@b.com",
                                "areaAtuacao": "Dev",
                                "nivelCarreira": "Senior"
                            }
                        """))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nivelCarreira").value("Senior"));
    }

    @Test
    void deveDeletarUsuario() throws Exception {
        mvc.perform(delete("/usuarios/1"))
                .andExpect(status().isNoContent());
    }
}
