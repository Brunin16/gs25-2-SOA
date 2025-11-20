# 📘 Global Solution 2025 – Plataforma de Trilhas e Competências 

Este projeto consiste no desenvolvimento de uma API RESTful para gerenciamento de **Usuários**, **Trilhas**, **Competências**, **Matrículas** e relacionamentos **N:N**, incluindo autenticação JWT em ambiente de produção e ambiente de desenvolvimento simplificado com banco em memória.

## 🚀 Stack Tecnológica

**Back-end**
- Java 17
- Spring Boot 3.x
- Spring Web
- Spring Data JPA
- Spring Security (JWT no profile prod)
- H2 Database (dev)
- Oracle Database (prod)
- Lombok
- Swagger (Springdoc OpenAPI)
- JUnit 5 + Mockito


## 🔐 Segurança (Spring Security + JWT)

### Desenvolvimento (dev)
- Banco H2
- Sem autenticação
- DatabaseSeeder habilitado

Rodar:
```bash
mvn spring-boot:run -Dspring-boot.run.profiles=dev
```

### Produção (prod)
- Banco Oracle
- JWT habilitado
- Login:
```
POST /auth/login
{
  "username": "admin",
  "password": "admin"
}
```

Header:
```
Authorization: Bearer <token>
```

Rodar:
```bash
mvn spring-boot:run -Dspring-boot.run.profiles=prod
```

## 🗂️ Entidades Principais

- Usuario
- Trilha
- Competência
- TrilhaCompetência (N:N)
- Matrícula

## 🌐 Endpoints Principais

### Usuários
- POST /usuarios
- GET /usuarios
- GET /usuarios/{id}
- PUT /usuarios/{id}
- DELETE /usuarios/{id}

### Trilhas
- POST /trilhas
- GET /trilhas
- GET /trilhas/{id}
- PUT /trilhas/{id}
- DELETE /trilhas/{id}

### Competências
- POST /competencias
- GET /competencias
- GET /competencias/{id}
- PUT /competencias/{id}
- DELETE /competencias/{id}

### Trilha ↔ Competência
- POST /trilhas/{trilhaId}/competencias
- GET /trilhas/{trilhaId}/competencias
- DELETE /trilhas/{trilhaId}/competencias/{competenciaId}

### Matrículas
- POST /matriculas
- GET /matriculas
- GET /matriculas/{id}
- GET /matriculas/usuario/{usuarioId}
- GET /matriculas/trilha/{trilhaId}
- PATCH /matriculas/{id}/status?status=NOVO_STATUS
- DELETE /matriculas/{id}

## 🧬 DatabaseSeeder (Somente no Dev)
- Competências: Java, Spring, SQL
- Trilha Back-end
- Ligações N:N
- Usuário teste
- Matrícula teste

## 🧪 Testes Automatizados
Testes completos com Mockito para todos os controllers:
- UsuarioControllerTest
- TrilhaControllerTest
- CompetenciaControllerTest
- TrilhaCompetenciaControllerTest
- MatriculaControllerTest

```bash
mvn clean test
```

## 📘 Swagger
```
http://localhost:8080/swagger-ui/index.html
```

## 🏗 Build
```bash
mvn clean package
```
## 👥 Membros
- Bruno Eduardo - rm558303