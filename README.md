# 🔗 Lynkar

**Sistema de Gerenciamento de Links**

Lynkar é uma aplicação full-stack para gerenciamento centralizado de links, permitindo que usuários criem, organizem e compartilhem seus links de forma segura e eficiente.

> Projeto em desenvolvimento

---

## 📋 Tabela de Conteúdos

- [Visão Geral](#visão-geral)
- [Features](#features)
- [Stack Tecnológico](#stack-tecnológico)
- [Estrutura do Projeto](#estrutura-do-projeto)
- [Pré-requisitos](#pré-requisitos)
- [Instalação e Execução](#instalação-e-execução)
- [Documentação da API](#documentação-da-api)
- [Estrutura do Banco de Dados](#estrutura-do-banco-de-dados)
- [Arquitetura e Padrões](#arquitetura-e-padrões)
- [Componentes Desenvolvidos](#componentes-desenvolvidos)
- [Contribuindo](#contribuindo)

---

## 🎯 Visão Geral

Lynkar é uma plataforma moderna de gerenciamento de links que oferece:
- Autenticação segura baseada em JWT
- Gerenciamento de usuários
- Interface limpa e intuitiva
- Infraestrutura containerizada com Docker
- API RESTful completa

---

## ✨ Features

### Backend
- ✅ **Autenticação e Autorização**
  - Registro de usuários
  - Login com JWT
  - Refresh token para renovação de sessão
  - Validação de credenciais com BCrypt

- ✅ **Gerenciamento de Usuários**
  - Criação de conta
  - Perfil de usuário
  - Atualização de dados
  - Controle de ativação de contas

- ✅ **Segurança**
  - Criptografia de senhas com BCrypt
  - JWT com expiração configurável
  - Filter de autenticação
  - Tratamento global de exceções

- ✅ **API**
  - Endpoints RESTful
  - Resposta padronizada em JSON
  - Validação de dados
  - Documentação com Swagger/OpenAPI

- ✅ **Banco de Dados**
  - PostgreSQL
  - Migrations automáticas com Flyway
  - ORM com Spring Data JPA

### Frontend
- Em desenvolvimento

---

## 🛠️ Stack Tecnológico

### Backend
- **Framework:** Spring Boot 4.0.0
- **Linguagem:** Java 25
- **Banco de Dados:** PostgreSQL 18
- **ORM:** Spring Data JPA + Hibernate
- **Autenticação:** JWT (JJWT 0.13.0)
- **Criptografia:** BCrypt
- **Mapeamento:** MapStruct 1.6.3
- **Documentação API:** SpringDoc OpenAPI 2.8.14
- **Build Tool:** Maven
- **Containerização:** Docker
- **Migrations:** Flyway
- **Logging:** Lombok

### Frontend
- Estrutura criada, desenvolvimento em andamento

### Infraestrutura
- Docker
- Docker Compose
- PostgreSQL em container

---

## 📁 Estrutura do Projeto

```
Lynkar/
├── backend/
│   ├── Lynkar/
│   │   ├── pom.xml
│   │   ├── Dockerfile
│   │   ├── mvnw / mvnw.cmd
│   │   └── src/
│   │       ├── main/
│   │       │   ├── java/com/br/lynkar/Lynkar/
│   │       │   │   ├── LynkarApplication.java
│   │       │   │   ├── config/
│   │       │   │   │   ├── BeanConfig.java
│   │       │   │   │   ├── exception/
│   │       │   │   │   │   ├── GlobalHandleException.java
│   │       │   │   │   │   ├── AuthenticationException.java
│   │       │   │   │   │   └── LynkarBusinessException.java
│   │       │   │   │   ├── security/
│   │       │   │   │   │   ├── SecurityConfig.java
│   │       │   │   │   │   ├── JwtAuthenticationFilter.java
│   │       │   │   │   │   └── SecurityConstants.java
│   │       │   │   │   └── doc/
│   │       │   │   ├── controller/
│   │       │   │   │   ├── AuthController.java
│   │       │   │   │   └── UserController.java
│   │       │   │   ├── service/
│   │       │   │   │   ├── AuthService.java
│   │       │   │   │   ├── JwtService.java
│   │       │   │   │   └── UserService.java
│   │       │   │   ├── mapper/
│   │       │   │   │   ├── AuthMapper.java
│   │       │   │   │   └── UserMapper.java
│   │       │   │   ├── repository/
│   │       │   │   │   └── UserRepository.java
│   │       │   │   ├── model/
│   │       │   │   │   └── UserEntity.java
│   │       │   │   └── dto/
│   │       │   │       ├── auth/
│   │       │   │       │   ├── AuthResponseDTO.java
│   │       │   │       │   └── RefreshTokenDTO.java
│   │       │   │       ├── common/
│   │       │   │       │   └── ApiResponse.java
│   │       │   │       ├── user/
│   │       │   │       │   ├── UserCreateDTO.java
│   │       │   │       │   ├── UserLoginDTO.java
│   │       │   │       │   ├── UserResponseDTO.java
│   │       │   │       │   └── UserAuthDataDTO.java
│   │       │   │       └── link/
│   │       │   ├── resources/
│   │       │   │   ├── application.properties
│   │       │   │   ├── db/migration/
│   │       │   │   │   └── V1__create_users_table.sql
│   │       │   │   ├── static/
│   │       │   │   └── templates/
│   │       │   └── test/
│   │       │       └── ...
│   │       └── target/
│   └── docker-compose.yml
├── frontend/
│   └── [Em desenvolvimento]
├── infra/
│   ├── docker-compose.yml
│   ├── .env
│   └── postgres/
├── docs/
│   └── [Documentação do projeto]
└── README.md
```

---

## 📦 Pré-requisitos

Certifique-se de ter instalado:

- **Java 25** ou superior
- **Maven 3.8+**
- **Docker** e **Docker Compose**
- **PostgreSQL 18** (ou use Docker)
- **Git**

---

## 🚀 Instalação e Execução

### 1. Clonar o Repositório

```bash
git clone https://github.com/Guids26/Lynkar.git
cd Lynkar
```

### 2. Configurar Variáveis de Ambiente

Crie um arquivo `.env` no diretório `infra/`:

```env
# Database Configuration
DATABASE_HOST=localhost
DATABASE_PORT=5432
DATABASE_DB=lynkar
DATABASE_USER=postgres
DATABASE_PASSWORD=seu_password

# JWT Configuration
JWT_SECRET=sua_chave_secreta_muito_longa_e_segura_aqui
```

### 3. Iniciar Infraestrutura (PostgreSQL)

```bash
cd infra
docker-compose up -d
```

Verifique se o PostgreSQL está rodando:

```bash
docker ps
```

### 4. Compilar e Executar Backend

```bash
cd backend/Lynkar
mvn clean install
mvn spring-boot:run
```

A aplicação estará disponível em `http://localhost:8080`

### 5. Acessar Documentação da API

Swagger UI: `http://localhost:8080/swagger-ui.html`

OpenAPI JSON: `http://localhost:8080/v3/api-docs`

---

## 📡 Documentação da API

### Endpoints Implementados

#### 🔐 Autenticação

**Registro de Usuário**
```http
POST /api/auth/register
Content-Type: application/json

{
  "email": "usuario@example.com",
  "password": "senha123",
  "name": "Nome do Usuário"
}

Response: 201 Created
{
  "data": null,
  "message": "Recurso criado com sucesso",
  "status": 201
}
```

**Login**
```http
POST /api/auth/login
Content-Type: application/json

{
  "email": "usuario@example.com",
  "password": "senha123"
}

Response: 200 OK
{
  "data": {
    "id": "123e4567-e89b-12d3-a456-426614174000",
    "email": "usuario@example.com",
    "name": "Nome do Usuário",
    "accessToken": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
    "accessTokenExpiresIn": 60000,
    "refreshToken": "550e8400-e29b-41d4-a716-446655440000",
    "refreshTokenExpiresIn": 1701950400000
  },
  "message": "Sucesso",
  "status": 200
}
```

**Refresh Token**
```http
POST /api/auth/refreshToken
Content-Type: application/json

{
  "refreshToken": "550e8400-e29b-41d4-a716-446655440000"
}

Response: 200 OK
{
  "data": {
    "accessToken": "novo_jwt_token...",
    "accessTokenExpiresIn": 60000,
    "refreshToken": "novo_refresh_token...",
    "refreshTokenExpiresIn": 1701950400000
  },
  "message": "Sucesso",
  "status": 200
}
```

#### 👤 Usuários

**Teste de Conexão (Requer Autenticação)**
```http
GET /api/user/teste
Authorization: Bearer {accessToken}

Response: 200 OK
"teste"
```

---

## 🗄️ Estrutura do Banco de Dados

### Tabela: USERS

```sql
CREATE TABLE USERS (
  id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
  name VARCHAR(255) NOT NULL,
  email VARCHAR(255) NOT NULL UNIQUE,
  password_hash VARCHAR(255) NOT NULL,
  is_active BOOLEAN NOT NULL DEFAULT TRUE,
  created_at TIMESTAMP NOT NULL DEFAULT NOW(),
  updated_at TIMESTAMP NOT NULL DEFAULT NOW(),
  refresh_token UUID,
  refresh_token_expires_in TIMESTAMP
);
```

**Campos:**
- `id`: Identificador único (UUID)
- `name`: Nome do usuário
- `email`: Email único do usuário
- `password_hash`: Senha criptografada com BCrypt
- `is_active`: Flag de ativação da conta
- `created_at`: Data de criação
- `updated_at`: Data de última atualização
- `refresh_token`: Token para renovação de sessão
- `refresh_token_expires_in`: Data de expiração do refresh token

**Migrations:**
- `V1__create_users_table.sql`: Criação da tabela de usuários (Flyway)

---

## 🏗️ Arquitetura e Padrões

### Arquitetura em Camadas

A aplicação segue o padrão de arquitetura em camadas:

```
┌─────────────────────────────────────┐
│         Controllers                 │
│  (Recebem e tratam requisições)    │
└──────────────┬──────────────────────┘
               │
┌──────────────▼──────────────────────┐
│         Services                    │
│  (Lógica de negócio)               │
└──────────────┬──────────────────────┘
               │
┌──────────────▼──────────────────────┐
│       Repositories                  │
│  (Acesso ao banco de dados)        │
└──────────────┬──────────────────────┘
               │
┌──────────────▼──────────────────────┐
│      Database (PostgreSQL)          │
└─────────────────────────────────────┘
```

### Padrões Utilizados

1. **DTO (Data Transfer Object)**
   - `UserCreateDTO`: Dados para criação de usuário
   - `UserLoginDTO`: Dados para login
   - `AuthResponseDTO`: Resposta de autenticação
   - `ApiResponse<T>`: Resposta padrão da API

2. **Mapper Pattern**
   - MapStruct para mapeamento entre entidades e DTOs
   - `AuthMapper`: Mapeamento de dados de autenticação
   - `UserMapper`: Mapeamento de dados de usuário

3. **Service Pattern**
   - Isolamento da lógica de negócio
   - `AuthService`: Serviço de autenticação
   - `UserService`: Serviço de gerenciamento de usuários
   - `JwtService`: Serviço de geração e validação de JWT

4. **Repository Pattern**
   - Spring Data JPA
   - `UserRepository`: Acesso aos dados de usuários

5. **Exception Handling**
   - Tratamento centralizado com `@ControllerAdvice`
   - Exceções customizadas: `LynkarBusinessException`, `AuthenticationException`
   - `GlobalHandleException`: Handler global

6. **Security Pattern**
   - Filter Chain com `JwtAuthenticationFilter`
   - `SecurityConfig`: Configuração de segurança
   - Integração com Spring Security

---

## 🔧 Componentes Desenvolvidos

### Controllers

#### AuthController
- **Responsabilidade:** Gerenciar endpoints de autenticação
- **Métodos:**
  - `POST /api/auth/register` - Registrar novo usuário
  - `POST /api/auth/login` - Fazer login
  - `POST /api/auth/refreshToken` - Renovar token de acesso

#### UserController
- **Responsabilidade:** Gerenciar endpoints de usuários
- **Métodos:**
  - `GET /api/user/teste` - Endpoint de teste (requer autenticação)

### Services

#### AuthService
- Registro de usuários
- Login e validação de credenciais
- Geração de tokens JWT
- Gerenciamento de refresh tokens
- Validação de refresh tokens

#### JwtService
- Geração de tokens JWT com claims personalizados
- Validação de tokens
- Extração de informações do token
- Cálculo de tempo de expiração

#### UserService
- Criação de usuários
- Busca de dados de login por email
- Busca de dados de login por refresh token
- Atualização de refresh token
- Gerenciamento de usuários

### Mappers

#### AuthMapper
- Mapeamento de `UserAuthDataDTO` para `AuthResponseDTO`

#### UserMapper
- Mapeamento entre `UserEntity` e `UserResponseDTO`
- Mapeamento entre `UserCreateDTO` e `UserEntity`

### Repositories

#### UserRepository (Spring Data JPA)
- `findByEmail(String email)` - Buscar usuário por email
- `existsByEmail(String email)` - Verificar existência de email
- `findFirstByRefreshToken(UUID refreshToken)` - Buscar por refresh token

### Models

#### UserEntity
- `id` (UUID) - Identificador único
- `name` - Nome do usuário
- `email` - Email único
- `password` - Senha criptografada
- `active` - Status ativo/inativo
- `createdAt` - Data de criação
- `updatedAt` - Data de atualização
- `refreshToken` - Token de renovação
- `refreshTokenExpiresIn` - Expiração do refresh token

### DTOs

#### Autenticação
- `UserCreateDTO` - Dados para criar usuário
- `UserLoginDTO` - Credenciais de login
- `AuthResponseDTO` - Resposta com tokens
- `RefreshTokenDTO` - Dados para renovação de token

#### Usuário
- `UserResponseDTO` - Dados públicos do usuário
- `UserAuthDataDTO` - Dados para autenticação

#### Comum
- `ApiResponse<T>` - Envelope padrão para respostas

### Configuração

#### BeanConfig
- Configuração de `PasswordEncoder` (BCrypt)

#### SecurityConfig
- Configuração de Spring Security
- Filter chain com JWT
- Permissões de acesso público/privado
- URLs públicas: `/api/auth/**`, `/swagger-ui/**`, `/v3/api-docs/**`

#### SecurityConstants
- Definição de URLs públicas
- Constantes de segurança

### Exception Handling

#### AuthenticationException
- Exceção customizada para erros de autenticação

#### LynkarBusinessException
- Exceção customizada para erros de lógica de negócio

#### GlobalHandleException
- Handler centralizado de exceções
- Tratamento de múltiplos tipos de exceção
- Retorno padronizado de erros

---

## 🐳 Docker e Infraestrutura

### Dockerfile (Backend)
```dockerfile
# Build multi-stage
# Runtime lightweight
# Port: 8080
```

### Docker Compose (Infraestrutura)
```yaml
# PostgreSQL 18
# Volume persistente
# Network dedicada
# Configuração via .env
```

---

## 📝 Propriedades da Aplicação

**application.properties:**
```properties
server.port=8080
spring.application.name=Lynkar

# Database
spring.datasource.url=jdbc:postgresql://[host]:[port]/[db]
spring.datasource.username=[user]
spring.datasource.password=[password]
spring.datasource.driver-class-name=org.postgresql.Driver

# JPA/Hibernate
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true

# JWT
jwt.secret=[sua_chave_secreta]
jwt.expiration=60000
jwt.refreshToken.expiration=7d
```

---

## 🚦 Fluxo de Autenticação

```
Usuário
  │
  ├─► [POST] /api/auth/register
  │   └─► Criar novo usuário com senha criptografada
  │
  ├─► [POST] /api/auth/login
  │   └─► Validar credenciais
  │       └─► Gerar JWT + Refresh Token
  │
  └─► [GET] /api/user/teste (com Authorization header)
      └─► JwtAuthenticationFilter valida token
          └─► Se válido, processa a requisição
          └─► Se expirado, usar [POST] /api/auth/refreshToken
              └─► Gerar novo JWT com refresh token válido
```

---

## 🔐 Segurança

- ✅ Senhas criptografadas com BCrypt (força 12)
- ✅ JWT com chave secreta configurável
- ✅ Refresh tokens com UUID
- ✅ Expiração de tokens configurável
- ✅ CSRF desabilitado (API stateless)
- ✅ Filter de autenticação centralizado
- ✅ Validação de entrada em DTOs

---

## 📊 Status do Desenvolvimento

### ✅ Implementado
- [x] Autenticação com JWT
- [x] Registro de usuários
- [x] Login e validação
- [x] Refresh token
- [x] Segurança (Spring Security)
- [x] Banco de dados (PostgreSQL)
- [x] Migrations (Flyway)
- [x] Exception handling
- [x] API RESTful
- [x] Docker & Docker Compose
- [x] Swagger/OpenAPI

### ⏳ Em Desenvolvimento
- [ ] Frontend
- [ ] Funcionalidades de links
- [ ] Compartilhamento de links
- [ ] Categorias e tags
- [ ] Busca e filtros
- [ ] Testes automatizados
- [ ] CI/CD pipeline
- [ ] Documentação detalhada

### 📋 Planejado
- [ ] Autenticação OAuth2
- [ ] API GraphQL
- [ ] WebSocket para real-time
- [ ] Cache com Redis
- [ ] Backup automático
- [ ] Análise de acessos
- [ ] Mobile App

---

## 📚 Dependências Principais

```xml
<!-- Spring Boot -->
spring-boot-starter-web
spring-boot-starter-data-jpa
spring-boot-starter-security

<!-- Database -->
postgresql
spring-boot-starter-flyway

<!-- JWT -->
jjwt-api, jjwt-impl, jjwt-jackson

<!-- Utilities -->
lombok
mapstruct

<!-- Documentation -->
springdoc-openapi-starter-webmvc-ui
```

---

## 🤝 Contribuindo

Este é um projeto pessoal em desenvolvimento. Contribuições são bem-vindas!

### Passos para Contribuir

1. Faça um fork do projeto
2. Crie uma branch para sua feature (`git checkout -b feature/AmazingFeature`)
3. Commit suas mudanças (`git commit -m 'Add some AmazingFeature'`)
4. Push para a branch (`git push origin feature/AmazingFeature`)
5. Abra um Pull Request

---

## 📄 Licença

Este projeto está sob licença MIT. Veja o arquivo LICENSE para mais detalhes.

---

## 👨‍💻 Autor

**Guids26** - [GitHub](https://github.com/Guids26)

---

## 📞 Suporte

Para dúvidas ou problemas, abra uma issue no repositório.

---

## 🔗 Links Úteis

- [Documentação Spring Boot](https://spring.io/projects/spring-boot)
- [Spring Security](https://spring.io/projects/spring-security)
- [JWT (JSON Web Tokens)](https://jwt.io)
- [PostgreSQL](https://www.postgresql.org)
- [Docker](https://www.docker.com)

---

**Última atualização:** Dezembro 2025

