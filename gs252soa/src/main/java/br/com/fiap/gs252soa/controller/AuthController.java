package br.com.fiap.gs252soa.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.gs252soa.dtos.LoginRequestDTO;
import br.com.fiap.gs252soa.dtos.LoginResponseDTO;
import br.com.fiap.gs252soa.exceptions.BadRequestException;
import br.com.fiap.gs252soa.security.JwtService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Profile("prod")
public class AuthController {

    private final JwtService jwtService;

    @Value("${security.jwt.username}")
    private String configUsername;

    @Value("${security.jwt.password}")
    private String configPassword;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@Valid @RequestBody LoginRequestDTO dto) {

        if (!dto.username().equals(configUsername) || !dto.password().equals(configPassword)) {
            throw new BadRequestException("Credenciais inválidas.");
        }

        String token = jwtService.generateToken(dto.username());

        return ResponseEntity.ok(new LoginResponseDTO("Bearer", token));
    }
}