package br.com.fiap.gs252soa.api.config;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import br.com.fiap.gs252soa.models.Competencia;
import br.com.fiap.gs252soa.models.Matricula;
import br.com.fiap.gs252soa.models.Trilha;
import br.com.fiap.gs252soa.models.TrilhaCompetencia;
import br.com.fiap.gs252soa.models.TrilhaCompetenciaId;
import br.com.fiap.gs252soa.models.Usuario;
import br.com.fiap.gs252soa.repository.CompetenciaRepository;
import br.com.fiap.gs252soa.repository.MatriculaRepository;
import br.com.fiap.gs252soa.repository.TrilhaCompetenciaRepository;
import br.com.fiap.gs252soa.repository.TrilhaRepository;
import br.com.fiap.gs252soa.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;

@Component
@Profile("dev")
@RequiredArgsConstructor
public class DatabaseSeeder implements CommandLineRunner {

    private final CompetenciaRepository competenciaRepo;
    private final TrilhaRepository trilhaRepo;
    private final TrilhaCompetenciaRepository trilhaCompetenciaRepo;
    private final UsuarioRepository usuarioRepo;
    private final MatriculaRepository matriculaRepo;

    @Override
    public void run(String... args) throws Exception {

        // Criar Competências
        Competencia java = competenciaRepo.save(
                Competencia.builder()
                        .nome("Java")
                        .categoria("Back-end")
                        .descricao("Fundamentos da linguagem Java.")
                        .build()
        );

        Competencia spring = competenciaRepo.save(
                Competencia.builder()
                        .nome("Spring Boot")
                        .categoria("Framework")
                        .descricao("Desenvolvimento de APIs REST com Spring Boot.")
                        .build()
        );

        Competencia sql = competenciaRepo.save(
                Competencia.builder()
                        .nome("SQL")
                        .categoria("Banco de Dados")
                        .descricao("Consultas e modelagem SQL.")
                        .build()
        );

        // Criar Trilha
        Trilha backend = trilhaRepo.save(
                Trilha.builder()
                        .nome("Back-end Developer")
                        .descricao("Formação completa para dev back-end.")
                        .nivel("Intermediário")
                        .cargaHoraria(120)
                        .focoPrincipal("Java e Spring")
                        .build()
        );

        // Vincular competências à trilha
        trilhaCompetenciaRepo.saveAll(List.of(
                TrilhaCompetencia.builder()
                        .id(new TrilhaCompetenciaId(backend.getId(), java.getId()))
                        .trilha(backend)
                        .competencia(java)
                        .build(),

                TrilhaCompetencia.builder()
                        .id(new TrilhaCompetenciaId(backend.getId(), spring.getId()))
                        .trilha(backend)
                        .competencia(spring)
                        .build(),

                TrilhaCompetencia.builder()
                        .id(new TrilhaCompetenciaId(backend.getId(), sql.getId()))
                        .trilha(backend)
                        .competencia(sql)
                        .build()
        ));

        // Criar Usuário
        Usuario usuario = usuarioRepo.save(
                Usuario.builder()
                        .nome("Usuário Teste")
                        .email("teste@fiap.com")
                        .areaAtuacao("Tecnologia")
                        .nivelCarreira("Júnior")
                        .build()
        );

        // Criar Matrícula
        matriculaRepo.save(
                Matricula.builder()
                        .usuario(usuario)
                        .trilha(backend)
                        .status("ATIVA")
                        .build()
        );

        System.out.println("DATABASE SEED COMPLETO 🚀 (dev mode)");
    }
}
