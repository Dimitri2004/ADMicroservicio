package org.example.service;

import org.example.model.Libro;
import org.example.repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ConexionMongoService {

    @Autowired
    private LibroRepository libroRepository;

    public List<Libro> buscarLibros() {
        return libroRepository.findAll();
    }

    public Libro crearLibro(Libro libro) {
        return libroRepository.save(libro);
    }

    public boolean borrarLibro(Long id) {
        if (libroRepository.existsById(id)) {
            libroRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public Optional<Libro> libroPorID(Long id) {
        return libroRepository.findById(id);
    }

    public Optional<Libro> libroPorTitulo(String titulo) {
        // Assuming you might want to add a custom method to LibroRepository for this
        // For now, we'll filter all books.
        return libroRepository.findAll().stream()
                .filter(libro -> libro.getTitulo().equalsIgnoreCase(titulo))
                .findFirst();
    }
}
