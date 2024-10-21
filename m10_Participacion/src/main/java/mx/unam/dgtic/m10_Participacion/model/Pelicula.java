package mx.unam.dgtic.m10_Participacion.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pelicula {
    private Integer id;
    private String titulo;
    private String director;
    private Integer anio;
}
