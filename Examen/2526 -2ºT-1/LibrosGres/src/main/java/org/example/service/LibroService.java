package org.example.service;

import org.example.model.Libro;
import org.example.repository.LibroRepository;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class LibroService {
    private final LibroRepository libroRepository;

    public LibroService(LibroRepository libroRepository) {
        this.libroRepository = libroRepository;
    }

    public Libro createLibro(String titulo, String autor, int anoPublicacion) {
        Libro newLibro = new Libro(0, titulo, autor, anoPublicacion);
        return libroRepository.save(newLibro);
    }

    public Optional<Libro> getLibroById(int id) {
        return libroRepository.findById(id);
    }

    public List<Libro> getAllLibros() {
        return libroRepository.findAll();
    }

    public Libro updateLibro(int id, String titulo, String autor, int anoPublicacion) {
        return libroRepository.findById(id).map(existingLibro -> {
            existingLibro.setTitulo(titulo);
            existingLibro.setAutor(autor);
            existingLibro.setAnoPublicacion(anoPublicacion);
            return libroRepository.save(existingLibro);
        }).orElse(null); // Or throw an exception
    }

    public void deleteLibro(int id) {
        libroRepository.deleteById(id);
    }

    public Optional<Libro> getLibroByTitulo(String titulo) {
        return libroRepository.findByTitulo(titulo);
    }
}
