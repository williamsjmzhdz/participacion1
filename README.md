# Participación 1 - API de Películas

Esta API permite gestionar películas con los siguientes atributos:

- **id**: Identificador único de la película.
- **titulo**: Título de la película.
- **director**: Nombre del director de la película.
- **anio**: Año de lanzamiento de la película.

## Endpoints

### GET /api/peliculas

Obtiene la lista de todas las películas.

- **Ruta**: `/api/peliculas`
- **Respuestas posibles**:
  - 200 OK: Devuelve la lista de películas.

### GET /api/peliculas/{id}

Obtiene una película por su ID.

- **Ruta**: `/api/peliculas/{id}`
- **Respuestas posibles**:
  - 200 OK: Devuelve la película encontrada.
  - 404 Not Found: No se encuentra la película con el ID proporcionado.

### POST /api/peliculas

Crea una nueva película.

- **Ruta**: `/api/peliculas`
- **Respuestas posibles**:
  - 201 Created: La película ha sido creada exitosamente.
  - 400 Bad Request: Hay un error en la solicitud.

### PUT /api/peliculas/{id}

Reemplaza completamente una película por su ID.

- **Ruta**: `/api/peliculas/{id}`
- **Respuestas posibles**:
  - 200 OK: La película ha sido actualizada exitosamente.
  - 404 Not Found: No se encuentra la película con el ID proporcionado.

### PATCH /api/peliculas/{id}

Actualiza parcialmente una película por su ID.

- **Ruta**: `/api/peliculas/{id}`
- **Respuestas posibles**:
  - 200 OK: La película ha sido actualizada exitosamente.
  - 404 Not Found: No se encuentra la película con el ID proporcionado.

### DELETE /api/peliculas/{id}

Elimina una película por su ID.

- **Ruta**: `/api/peliculas/{id}`
- **Respuestas posibles**:
  - 204 No Content: La película ha sido eliminada exitosamente.
  - 404 Not Found: No se encuentra la película con el ID proporcionado.
