# Spring Boot API Starter

Production-ready REST API template with JWT authentication, PostgreSQL, Flyway migrations, and Docker.

![Java](https://img.shields.io/badge/Java-17-orange?logo=openjdk)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.2-brightgreen?logo=springboot)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-16-blue?logo=postgresql)
![JWT](https://img.shields.io/badge/JWT-0.12.5-black?logo=jsonwebtokens)
![Docker](https://img.shields.io/badge/Docker-Compose-2496ED?logo=docker)
![Swagger](https://img.shields.io/badge/Swagger-OpenAPI_3-85EA2D?logo=swagger)

## Features

- **JWT Authentication** — stateless token-based auth with register/login endpoints
- **Flyway Migrations** — versioned schema management, reproducible across environments
- **Swagger / OpenAPI 3** — interactive docs with Bearer token support at `/swagger-ui.html`
- **Global Error Handling** — consistent `ErrorResponse` structure across all endpoints
- **Input Validation** — bean validation on all request bodies with field-level error messages
- **Docker Compose** — one-command local setup (app + PostgreSQL with health checks)
- **Actuator** — health and metrics endpoints at `/actuator`

## Getting Started

```bash
docker-compose up --build
```

App starts at **http://localhost:8080**
Swagger UI: **http://localhost:8080/swagger-ui.html**

> PostgreSQL is automatically initialized with database `starter`, user `starter`, password `starter`.

## API Endpoints

### Auth

| Method | Endpoint | Auth | Description |
|--------|----------|------|-------------|
| POST | `/api/auth/register` | None | Register new user |
| POST | `/api/auth/login` | None | Login, get JWT |

### Products

| Method | Endpoint | Auth | Description |
|--------|----------|------|-------------|
| GET | `/api/products` | Required | List all products |
| GET | `/api/products/{id}` | Required | Get product by ID |
| POST | `/api/products` | Required | Create product |
| DELETE | `/api/products/{id}` | Required | Delete product |

## Example Usage

**Register:**
```bash
curl -X POST http://localhost:8080/api/auth/register \
  -H "Content-Type: application/json" \
  -d '{"email":"user@example.com","password":"secret123"}'
```

**Login:**
```bash
curl -X POST http://localhost:8080/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{"email":"user@example.com","password":"secret123"}'
# Returns: {"token":"eyJ..."}
```

**Create product (with token):**
```bash
curl -X POST http://localhost:8080/api/products \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer <token>" \
  -d '{"name":"Widget","description":"A useful widget","price":9.99}'
```

## Project Structure

```
src/main/java/com/starter/api/
├── ApiApplication.java
├── auth/
│   ├── AuthController.java
│   ├── AuthService.java
│   ├── AuthResponse.java
│   ├── LoginRequest.java
│   ├── RegisterRequest.java
│   ├── JwtAuthFilter.java
│   └── JwtTokenProvider.java
├── user/
│   ├── User.java
│   ├── UserRepository.java
│   └── Role.java
├── product/
│   ├── Product.java
│   ├── ProductRepository.java
│   ├── ProductService.java
│   ├── ProductController.java
│   ├── CreateProductRequest.java
│   └── ProductResponse.java
├── config/
│   ├── SecurityConfig.java
│   └── OpenApiConfig.java
└── common/
    ├── ErrorResponse.java
    └── GlobalExceptionHandler.java

src/main/resources/
├── application.yml
└── db/migration/
    ├── V1__create_users_table.sql
    └── V2__create_products_table.sql
```

## Use as Template

1. Fork this repo
2. Rename the base package from `com.starter.api` to your own
3. Replace the `Product` domain with your own entity
4. Update `application.yml` with your database credentials
5. Set a strong `jwt.secret` (min 32 chars) in environment variables for production

## License

MIT License — see [LICENSE](LICENSE)
