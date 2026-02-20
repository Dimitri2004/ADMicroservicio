package org.example.controller;

import org.example.model.Libro;
import org.example.service.LibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("postgres/libros")
public class RestLibros {

    private final LibroService libroService;

    @Autowired
    public RestLibros(LibroService libroService) {
        this.libroService = libroService;
    }

    @GetMapping
    public ResponseEntity<List<Libro>> getAllLibros() {
        List<Libro> libros = libroService.getAllLibros();
        return new ResponseEntity<>(libros, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Libro> getLibroById(@PathVariable String id) {
        Optional<Libro> libro = libroService.getLibroById(id);
        return libro.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                    .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Libro> createLibro(@RequestBody Libro datos) {
        Libro newLibro = libroService.createLibro(
                datos.getTitulo(),
                datos.getAutor(),
                datos.getAnoPublicacion()
        );
        return new ResponseEntity<>(newLibro, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Libro> updateLibro(@PathVariable String id, @RequestBody Libro libroRequest) {
        Libro updatedLibro = libroService.updateLibro(
                id,
                libroRequest.getTitulo(),
                libroRequest.getAutor(),
                libroRequest.getAnoPublicacion()
        );
        return updatedLibro != null ? new ResponseEntity<>(updatedLibro, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLibro(@PathVariable String id) {
        libroService.deleteLibro(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Inner class for request body
}
