# 🏫 API Reservas Espacios Unimag

Una API REST robusta desarrollada con Java y Spring Boot para gestionar la reserva de espacios físicos universitarios como salones, canchas, auditorios y más.

## 🚀 Características Principales

- **🔐 Autenticación JWT**: Sistema seguro de registro e inicio de sesión
- **👥 Gestión de Roles**: Control de acceso con roles (administrador, estudiante, etc.)
- **🏫 Gestión de Espacios**: CRUD completo para espacios disponibles
- **📆 Sistema de Reservas**: Reserva y gestión de espacios por parte de usuarios
- **✅ Validaciones Automáticas**: Validación robusta de datos de entrada
- **❌ Manejo de Errores**: Respuestas claras y estructuradas para errores

## 🛠️ Tecnologías Utilizadas

- **Java 17** - Lenguaje de programación
- **Spring Boot** - Framework principal
- **Spring Security** - Seguridad y autenticación
- **JWT** - Tokens de autenticación
- **Spring Data JPA** - Acceso a datos
- **MapStruct** - Mapeo de DTO's y entidades
- **PostgreSQL** - Base de datos
- **Maven** - Gestión de dependencias
- **Lombok** - Reducción de código boilerplate

## 📋 Prerrequisitos

Antes de ejecutar la aplicación, asegúrate de tener instalado:

- Java 17 o superior
- Maven 3.6+
- PostgreSQL 12+
- Git

## ⚡ Instalación y Configuración

### 1. Clonar el repositorio

```bash
git clone https://github.com/MatteoAngulo/reservasEspaciosUnimag.git
cd reservasEspaciosUnimag
```

### 2. Configurar la base de datos

Crea una base de datos PostgreSQL:

```sql
CREATE DATABASE reservas_db;
```

### 3. Configurar variables de entorno

Crea un archivo `application.properties` en `src/main/resources/` con:

```properties
# Configuración de base de datos
spring.datasource.url=jdbc:postgresql://localhost:5432/reservas_db
spring.datasource.username=tu_usuario
spring.datasource.password=tu_contraseña
spring.datasource.driver-class-name=org.postgresql.Driver

# Configuración JPA
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect

# Configuración JWT
jwt.secret=mi_clave_secreta_super_segura
jwt.expiration=86400000

# Configuración del servidor
server.port=8080
```

### 4. Ejecutar la aplicación

```bash
./mvnw spring-boot:run
```

La API estará disponible en `http://localhost:8080`

## 📖 Endpoints Principales

### Autenticación

```http
POST /api/auth/register    # Registrar nuevo usuario
POST /api/auth/login       # Iniciar sesión
```

### Gestión de Espacios

```http
GET    /api/espacios       # Listar todos los espacios
POST   /api/espacios       # Crear nuevo espacio
GET    /api/espacios/{id}  # Obtener espacio por ID
PUT    /api/espacios/{id}  # Actualizar espacio
DELETE /api/espacios/{id}  # Eliminar espacio
```

### Gestión de Reservas

```http
GET    /api/reservas       # Listar reservas del usuario
POST   /api/reservas       # Crear nueva reserva
GET    /api/reservas/{id}  # Obtener reserva por ID
PUT    /api/reservas/{id}  # Actualizar reserva
DELETE /api/reservas/{id}  # Cancelar reserva
```

## 🔧 Ejemplos de Uso

### Registro de Usuario

```bash
curl -X POST http://localhost:8080/api/auth/register \
  -H "Content-Type: application/json" \
  -d '{
    "nombre": "Juan Pérez",
    "email": "juan@unimag.edu.co",
    "password": "password123",
    "rol": "ESTUDIANTE"
  }'
```

### Crear Reserva

```bash
curl -X POST http://localhost:8080/api/reservas \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer YOUR_JWT_TOKEN" \
  -d '{
    "espacioId": 1,
    "fechaInicio": "2024-12-15T10:00:00",
    "fechaFin": "2024-12-15T12:00:00",
    "descripcion": "Reunión de estudio"
  }'
```

## 🔗 Frontend

Esta API está conectada con un frontend desarrollado por el equipo ([@Lord-Jospe](https://github.com/Lord-Jospe)). Puedes encontrarlo en:

👉 [Frontend Reservas Unimag](https://github.com/Lord-Jospe/fronted-Reservas_Unimag)

## 👥 Autores

- **Andrés Escobar** - [@AndresMes](https://github.com/AndresMes) - Desarrollador
- **Mateo Angulo** - [@MatteoAngulo](https://github.com/MatteoAngulo) - Desarrollador
- **Leonel Pereira** - [@LeonelP7](https://github.com/LeonelP7) - Desarrollador

## 📞 Contacto

🔗 **Link del Proyecto**: [https://github.com/MatteoAngulo/reservasEspaciosUnimag](https://github.com/MatteoAngulo/reservasEspaciosUnimag)

---

⭐ Si te ha sido útil este proyecto, ¡no olvides darle una estrella!
