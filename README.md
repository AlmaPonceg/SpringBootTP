# API REST de Productos - Programación III

Trabajo práctico integrador para la **Tecnicatura Universitaria en Programación** (a distancia).  
El objetivo es construir una API REST completa y profesional para la **gestión de productos** usando **Spring Boot 3**.

---

## 🎯 Objetivo general

- Diseñar y construir una API REST desde cero.
- Aplicar arquitectura en capas (`controller`, `service`, `repository`).
- Trabajar con **DTOs** para desacoplar el modelo de dominio.
- Validar datos de entrada con **Bean Validation**.
- Manejar errores de forma centralizada con `@ControllerAdvice`.
- Persistir datos con **Spring Data JPA + H2**.
- Documentar la API con **Swagger / OpenAPI**.
- Probar todos los endpoints de forma interactiva.

---

## ⚙ Tecnologías utilizadas

- Java 17
- Spring Boot 3
  - spring-boot-starter-web
  - spring-boot-starter-data-jpa
  - spring-boot-starter-validation
- Base de datos en memoria **H2**
- Lombok
- Spring Boot DevTools (desarrollo)
- **springdoc-openapi-starter-webmvc-ui** (Swagger UI)

---

## 🚀 Cómo clonar y ejecutar el proyecto

1. Clonar el repositorio:

```bash
git clone https://github.com/tu-usuario/productos-api.git
cd productos-api
```

2. Compilar y descargar dependencias:

```bash
mvn clean install
```

3. Ejecutar la aplicación:

```bash
mvn spring-boot:run
```

La app se levanta por defecto en:  
`http://localhost:8080`

---

## 🌐 Endpoints principales

Base URL: `/api/productos`

| Método | Ruta                           | Descripción                                      |
|--------|--------------------------------|--------------------------------------------------|
| GET    | `/api/productos`              | Listar todos los productos                       |
| GET    | `/api/productos/{id}`         | Obtener un producto por ID                       |
| GET    | `/api/productos/categoria/{c}`| Listar productos filtrando por categoría         |
| POST   | `/api/productos`              | Crear un nuevo producto                          |
| PUT    | `/api/productos/{id}`         | Actualizar un producto completo                  |
| PATCH  | `/api/productos/{id}/stock`   | Actualizar solo el stock de un producto          |
| DELETE | `/api/productos/{id}`         | Eliminar un producto (retorna 204 No Content)    |

> Categorías válidas (enum `Categoria`): `ELECTRONICA`, `ROPA`, `ALIMENTOS`, `HOGAR`, `DEPORTES`.

---

## 📚 Swagger UI y consola H2

- **Swagger UI** (documentación + testing de la API):

  - `http://localhost:8080/swagger-ui/index.html`

- **OpenAPI JSON**:

  - `http://localhost:8080/v3/api-docs`

- **Consola H2**:

  - `http://localhost:8080/h2-console`
  - JDBC URL: `jdbc:h2:mem:productosdb`
  - Usuario: `sa`
  - Password: *(vacía)*

---

## 🧪 Guía de pruebas sugerida (según consigna)

1. **Crear productos** (POST `/api/productos`):
   - Crear al menos 5 productos de distintas categorías.
   - Probar crear un producto sin nombre → debe dar **400** (validación).
   - Probar crear un producto con precio negativo → debe dar **400**.
   - Capturar pantalla de una creación exitosa en Swagger UI.

2. **Listar productos**:
   - GET `/api/productos`: listar todo.
   - GET `/api/productos/categoria/ELECTRONICA`: filtrar por categoría.
   - Capturar pantalla de los resultados.

3. **Obtener por ID**:
   - GET `/api/productos/{id}` con un ID existente.
   - GET `/api/productos/{id}` con un ID inexistente → debe dar **404**.
   - Capturar pantalla del error 404 (se ve el JSON de `ErrorResponse`).

4. **Actualizar producto**:
   - PUT `/api/productos/{id}` con un body completo válido.
   - PATCH `/api/productos/{id}/stock` para cambiar solo el stock.
   - Capturar pantalla de ambas operaciones exitosas.

5. **Eliminar producto**:
   - DELETE `/api/productos/{id}` → debe retornar **204 No Content**.
   - Luego hacer GET al mismo ID → debe retornar **404**.

6. **Verificar en H2**:
   - Entrar a `http://localhost:8080/h2-console`.
   - Usar la URL `jdbc:h2:mem:productosdb`.
   - Ejecutar `SELECT * FROM PRODUCTO;`
   - Capturar pantalla con los datos persistidos.

---

## 💭 Conclusiones personales (para completar en el TP)

- Aprendizajes principales sobre APIs REST con Spring Boot.
- Dificultades encontradas y cómo se resolvieron.
- Ventajas de usar DTOs, validaciones y manejo global de errores.
- Qué partes te resultaron más útiles pensando en proyectos reales.

---


