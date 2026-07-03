# Fifa API

Projeto final da disciplina de Spring Boot — **Tema: Gestão de Ingressos (Eventos Culturais)**.

API REST desenvolvida em **Spring Boot** para gerenciamento de eventos, locais, usuários e venda de ingressos, com controle de lotação máxima e estatísticas de vendas. Conta com autenticação e autorização via **JWT**.

## Equipe

| Nome Completo | RGM |
|---|---|
| _Thiago Henrique P Oliveira_ | _43672973_ |


## Tecnologias utilizadas

- Java 21
- Spring Boot
- Spring Data JPA
- Spring Security
- MySQL
- JWT (Auth0 java-jwt)
- Lombok
- Bean Validation
- SpringDoc OpenAPI (Swagger)
- Gradle

## Funcionalidades

- Cadastro e autenticação de usuários (login com JWT)
- Perfis de acesso (`ADMIN` e `CLIENTE`)
- CRUD de locais de eventos
- CRUD de eventos
- Compra, listagem, cancelamento e resumo de ingressos
- Documentação interativa via Swagger

### Regras de negócio

- Um evento não pode vender mais ingressos do que a capacidade máxima do seu local.
- Um mesmo usuário pode comprar no máximo **5 ingressos** para o mesmo evento.

### Endpoint de resumo estatístico (`GET /ingressos/resumo`)

Calculado em memória (via streams) e retorna:
- Total de ingressos vendidos
- Receita total arrecadada
- Taxa de ocupação média dos eventos (ingressos vendidos / capacidade máxima)
- Evento com maior número de vendas

## Pré-requisitos

- [JDK 21](https://adoptium.net/)
- [MySQL](https://dev.mysql.com/downloads/) rodando localmente
- Gradle (o projeto já inclui o Gradle Wrapper, não é necessário instalar)

## Configuração

1. Crie um banco de dados MySQL:

```sql
CREATE DATABASE fifa_db;
```

2. Configure as variáveis de acesso ao banco e o segredo do JWT em `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/fifa_db
spring.datasource.username=SEU_USUARIO
spring.datasource.password=SUA_SENHA

api.security.token.secret=SEU_SEGREDO_JWT
```

> ⚠️ **Importante:** evite subir credenciais reais no `application.properties` para o GitHub. O ideal é usar variáveis de ambiente ou um arquivo `application-local.properties` ignorado pelo Git.

## Como executar

Clone o repositório e execute:

```bash
# Linux/Mac
./gradlew bootRun

# Windows
gradlew.bat bootRun
```

A aplicação sobe por padrão em `http://localhost:8080`.

## Documentação da API (Swagger)

Com a aplicação rodando, acesse:

```
http://localhost:8080/swagger-ui.html
```

## Principais endpoints

| Método | Endpoint | Descrição | Acesso |
|--------|----------|-----------|--------|
| POST | `/usuarios` | Cadastrar usuário | Público |
| POST | `/auth/login` | Autenticar e gerar token JWT | Público |
| GET | `/usuarios` | Listar usuários | Autenticado |
| GET | `/usuarios/{id}` | Buscar usuário por ID | Autenticado |
| DELETE | `/usuarios/{id}` | Remover usuário | Autenticado |
| POST | `/locais` | Cadastrar local | Autenticado |
| GET | `/locais` | Listar locais | Autenticado |
| GET | `/locais/{id}` | Buscar local por ID | Autenticado |
| PUT | `/locais/{id}` | Atualizar local | Autenticado |
| DELETE | `/locais/{id}` | Remover local | Autenticado |
| POST | `/eventos` | Cadastrar evento | Autenticado |
| GET | `/eventos` | Listar eventos | Autenticado |
| GET | `/eventos/{id}` | Buscar evento por ID | Autenticado |
| PUT | `/eventos/{id}` | Atualizar evento | Autenticado |
| DELETE | `/eventos/{id}` | Remover evento | Autenticado |
| POST | `/ingressos` | Comprar ingresso | Autenticado |
| GET | `/ingressos?usuarioId=` | Listar ingressos de um usuário | Autenticado |
| GET | `/ingressos/{id}` | Buscar ingresso por ID | Autenticado |
| GET | `/ingressos/resumo` | Resumo de ingressos vendidos | Autenticado |
| DELETE | `/ingressos/{id}` | Cancelar ingresso | Autenticado |

Endpoints marcados como **Autenticado** exigem o envio do token JWT no header:

```
Authorization: Bearer SEU_TOKEN
```

## Autenticação

1. Cadastre um usuário via `POST /usuarios`.
2. Faça login via `POST /auth/login` enviando `email` e `senha`.
3. Utilize o token JWT retornado para acessar os demais endpoints.

## Estrutura do projeto

```
src/main/java/com/example/fifa
├── controller     # Endpoints REST
├── service        # Regras de negócio
├── repository     # Acesso a dados (Spring Data JPA)
├── model          # Entidades JPA
│   └── dto        # Objetos de transferência de dados
├── security        # Configuração de segurança e JWT
└── exception       # Tratamento global de exceções
```

## Autor

Projeto desenvolvido por Thiago Henrique.
