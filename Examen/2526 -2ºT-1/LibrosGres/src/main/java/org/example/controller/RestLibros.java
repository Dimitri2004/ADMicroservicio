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
@RequestMapping("/libros")
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
    public ResponseEntity<Libro> createLibro(@RequestBody LibroRequest libroRequest) {
        Libro newLibro = libroService.createLibro(
                libroRequest.getTitulo(),
                libroRequest.getAutor(),
                libroRequest.getAnoPublicacion()
        );
        return new ResponseEntity<>(newLibro, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Libro> updateLibro(@PathVariable String id, @RequestBody LibroRequest libroRequest) {
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
    static class LibroRequest {
        private String titulo;
        private String autor;
        private int anoPublicacion;

        public String getTitulo() {
            return titulo;
        }

        public void setTitulo(String titulo) {
            this.titulo = titulo;
        }

        public String getAutor() {
            return autor;
        }

        public void setAutor(String autor) {
            this.autor = autor;
        }

        public int getAnoPublicacion() {
            return anoPublicacion;
        }

        public void setAnoPublicacion(int anoPublicacion) {
            this.anoPublicacion = anoPublicacion;
        }
    }
}
