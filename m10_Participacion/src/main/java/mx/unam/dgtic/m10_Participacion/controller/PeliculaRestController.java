package mx.unam.dgtic.m10_Participacion.controller;

import mx.unam.dgtic.m10_Participacion.model.Pelicula;
import org.apache.catalina.connector.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("/api/peliculas")
public class PeliculaRestController {
    String[] nombres = new String[]{
            "Christian Castro Hernández",
            "Francisco Williams Jiménez Hernández",
            "Guillermo Romero Zuñiga",
            "José Emmanuel Espino Moya"
    };
    HashMap<Integer, Pelicula> peliculas;

    public PeliculaRestController(){
        peliculas = new HashMap<>();
        peliculas.put(0, new Pelicula(0, "Pulp Fiction", "Quentin Tarantino", 1994));
        peliculas.put(1, new Pelicula(1, "Entrevista con un Vampiro", "Neil Jordan", 1994));
        peliculas.put(2, new Pelicula(2, "Harry Potter y la Cámara Secreta", "Chris Columbus", 2002));
        peliculas.put(3, new Pelicula(3, "El Padrino", "Francis Ford Coppola", 1972));
    }

    @GetMapping(path = "/creditos", produces = MediaType.TEXT_HTML_VALUE)
    public String creditos(){
        String salida = "<div style=\"font-family:sans-serif\"><h2>Integrantes</h2><ul>";
        for(String nombre : nombres)
            salida += "<li>" + nombre + "</li>";
        salida += "</ul></div>";
        return salida;
    }

    @GetMapping(path = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HashMap<Integer, Pelicula>> getAll(){
        return new ResponseEntity<>(peliculas, HttpStatus.OK);
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Pelicula> getById(@PathVariable int id){
        Pelicula pelicula = peliculas.get(id);
        if(pelicula != null)
            return ResponseEntity.ok(pelicula);
        else
            return ResponseEntity.notFound().build();
    }

    @PostMapping(path = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Pelicula> addPelicula(@RequestBody Pelicula pelicula){
        //Se validan datos de entrada requeridos
        if(pelicula.getTitulo() == null || pelicula.getTitulo().isEmpty() ||
        pelicula.getAnio() == null || pelicula.getDirector() == null || pelicula.getDirector().isEmpty())
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        int id = 0;
        while(peliculas.containsKey(id)){
            id++;
        }
        pelicula.setId(id);
        peliculas.put(id, pelicula);
        return new ResponseEntity(pelicula, HttpStatus.CREATED);
    }

    @PutMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Pelicula> updateLibro(@PathVariable int id, @RequestBody Pelicula pelicula){
        pelicula.setId(id);
        //Se valida que exista la llave indicada
        if(peliculas.containsKey(id)){
            //Se valida que todos los campos estén presentes
            if(pelicula.getTitulo() == null || pelicula.getTitulo().isEmpty() ||
            pelicula.getAnio() == null || pelicula.getDirector() == null || pelicula.getDirector().isEmpty()){
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            peliculas.replace(id, pelicula);
            return ResponseEntity.ok(peliculas.get(id));
        }
        else
            return addPelicula(pelicula);
    }

    @PatchMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Pelicula> actualizaPelicula(@PathVariable int id, @RequestBody Pelicula pelicula){
        //Se verifica si el body está vacío
        if(pelicula == null)
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        //Se verifica si el id existe en la BBDD
        if(!peliculas.containsKey(id))
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        //Se recupera la entidad en BBDD
        Pelicula peliculaBD = peliculas.get(id);
        //Se setea el ID para que coincida con el del parámetro.
        pelicula.setId(id);
        if(pelicula.getAnio() != null)
            peliculaBD.setAnio(pelicula.getAnio());
        if(pelicula.getDirector() != null)
            peliculaBD.setDirector(pelicula.getDirector());
        if(pelicula.getTitulo() != null)
            peliculaBD.setTitulo(pelicula.getTitulo());
        peliculas.replace(id, peliculaBD);
        return ResponseEntity.ok(peliculaBD);
    }

    @DeleteMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Pelicula> deletePelicula(@PathVariable int id){
        if(peliculas.containsKey(id))
            return ResponseEntity.ok(peliculas.remove(id));
        else
            return ResponseEntity.notFound().build();
    }


}
