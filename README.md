# 🏫 API de Reservas de Espacios - Unimag

Este proyecto es una **API REST** desarrollada con **Java y Spring Boot**, que permite gestionar la **reserva de espacios físicos** (salones, canchas, auditorios, etc.) en una universidad. También incluye un sistema de autenticación con JWT y roles de usuario para controlar el acceso a las funcionalidades.

## 🚀 Tecnologías utilizadas

- ✅ Java 17
- ✅ Spring Boot
- ✅ Spring Security + JWT
- ✅ Spring Data JPA
- ✅ PostgreSQL
- ✅ Maven
- ✅ Lombok

> ⚙️ Incluye también un front-end conectado a esta API.

https://github.com/Lord-Jospe/fronted-Reservas_Unimag

## 📦 Funcionalidades principales

- 🔐 Registro e inicio de sesión de usuarios con JWT
- 👤 Gestión de roles (administrador, estudiante, etc.)
- 🏫 CRUD de espacios disponibles (salones, canchas, auditorios…)
- 📆 CRUD de reservas de espacios por parte de los usuarios
- ✅ Validaciones automáticas de datos
- ❌ Manejo de errores con respuestas claras

---

## ⚙️ Instalación y ejecución

### 1. Clona el repositorio

```bash
git clone https://github.com/tuusuario/reservasEspaciosUnimag.git
cd reservasEspaciosUnimag
```
### 2. Configura tu archivo application.properties o application.yml
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/reservas_db
spring.datasource.username=tu_usuario
spring.datasource.password=tu_contraseña

jwt.secret=mi_clave_secreta
jwt.expiration=86400000
```

### 3. Ejecuta la aplicación
```bash
./mvnw spring-boot:run
```
