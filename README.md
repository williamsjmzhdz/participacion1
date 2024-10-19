# Participación 1 - API de Películas

Esta es una API para gestionar películas con los siguientes atributos:

- **id**: Identificador único de la película.
- **titulo**: Título de la película.
- **director**: Nombre del director de la película.
- **anio**: Año de lanzamiento de la película.

## Endpoints

### GET /api/peliculas

Obtiene la lista de todas las películas.

### GET /api/peliculas/{id}

Obtiene una película por su ID.

### POST /api/peliculas

Crea una nueva película. El cuerpo de la solicitud debe incluir `titulo`, `director`, y `anio`.

### PUT /api/peliculas/{id}

Reemplaza completamente una película por su ID.

### PATCH /api/peliculas/{id}

Actualiza parcialmente una película por su ID.

### DELETE /api/peliculas/{id}

Elimina una película por su ID.
