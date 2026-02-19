package org.example.service;

import org.example.model.Libro;
import org.example.repository.LibroRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LibroService {
    private final LibroRepository libroRepository;

    public LibroService(LibroRepository libroRepository) {
        this.libroRepository = libroRepository;
    }

    public Libro createLibro(String titulo, String autor, int anoPublicacion) {
        Libro newLibro = new Libro(); // Use no-arg constructor
        newLibro.setTitulo(titulo);
        newLibro.setAutor(autor);
        newLibro.setAnoPublicacion(anoPublicacion);
        return libroRepository.save(newLibro);
    }

    public Optional<Libro> getLibroById(String id) {
        return libroRepository.findById(id);
    }

    public List<Libro> getAllLibros() {
        return libroRepository.findAll();
    }

    public Libro updateLibro(String id, String titulo, String autor, int anoPublicacion) {
        return libroRepository.findById(id).map(existingLibro -> {
            existingLibro.setTitulo(titulo);
            existingLibro.setAutor(autor);
            existingLibro.setAnoPublicacion(anoPublicacion);
            return libroRepository.save(existingLibro);
        }).orElse(null); // Or throw an exception if not found
    }

    public void deleteLibro(String id) {
        libroRepository.deleteById(id);
    }

    public Optional<Libro> getLibroByTitulo(String titulo) {
        return libroRepository.findByTitulo(titulo);
    }
}
