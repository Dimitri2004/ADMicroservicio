package org.example.repository;

import org.example.model.Libro;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class LibroRepository {
    private final List<Libro> libros = new ArrayList<>();
    private int nextId = 1;

    public Libro save(Libro libro) {
        if (libro.getId() == 0) { // New libro
            libro.setId(nextId++);
        } else { // Existing libro, update it
            deleteById(libro.getId()); // Remove old version
        }
        libros.add(libro);
        return libro;
    }

    public Optional<Libro> findById(int id) {
        return libros.stream()
                     .filter(libro -> libro.getId() == id)
                     .findFirst();
    }

    public List<Libro> findAll() {
        return new ArrayList<>(libros);
    }

    public void deleteById(int id) {
        libros.removeIf(libro -> libro.getId() == id);
    }

    public Optional<Libro> findByTitulo(String titulo) {
        return libros.stream()
                .filter(libro -> libro.getTitulo().equalsIgnoreCase(titulo))
                .findFirst();
    }
}
