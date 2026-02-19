package org.example.service;

import org.example.model.Libro; // Import the Libro model
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

@Service
public class ConexionService {

    @Autowired
    private RestTemplate restTemplate;

    // Assuming an external PostgreSQL service for Libros at this URL
    private static final String POSTGRES_BASE_URL_LIBRO = "http://localhost:8081/postgres/libros";

    public List<Libro> buscarLibros() {
        try {
            String url = POSTGRES_BASE_URL_LIBRO;
            ResponseEntity<List<Libro>> response = restTemplate.exchange(
                    url, HttpMethod.GET, null,
                    new ParameterizedTypeReference<List<Libro>>() {}
            );
            return response.getBody() != null ? response.getBody() : Collections.emptyList();
        } catch (HttpClientErrorException e) {
            System.out.println("Error al buscar libros: " + e.getMessage());
            return Collections.emptyList();
        }
    }

    public boolean borrarLibro(String id) {
        try {
            String url = POSTGRES_BASE_URL_LIBRO + "/" + id;
            restTemplate.exchange(
                    url, HttpMethod.DELETE, null, Void.class
            );
            return true;
        } catch (HttpClientErrorException e) {
            System.out.println("Error al borrar libro con ID " + id + ": " + e.getMessage());
            return false;
        }
    }

    public Libro crearLibro(Libro libro) {
        try {
            String url = POSTGRES_BASE_URL_LIBRO;
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<Libro> request = new HttpEntity<>(libro, headers);

            ResponseEntity<Libro> response = restTemplate.exchange(
                    url, HttpMethod.POST, request, Libro.class
            );
            return response.getBody();
        } catch (HttpClientErrorException e) {
            System.out.println("Error al crear libro: " + e.getMessage());
            return null;
        }
    }

    public Libro libroPorID(String id) {
        try {
            String url = POSTGRES_BASE_URL_LIBRO + "/" + id;
            ResponseEntity<Libro> response = restTemplate.exchange(url, HttpMethod.GET, null, Libro.class);
            return response.getBody();
        } catch (HttpClientErrorException e) {
            System.out.println("Error al buscar libro por ID " + id + ": " + e.getMessage());
            return null;
        }
    }

    public Libro libroPorTitulo(String titulo) {
        try {
            String url = POSTGRES_BASE_URL_LIBRO + "/titulo/" + titulo;
            ResponseEntity<List<Libro>> response = restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<List<Libro>>() {});
            List<Libro> libros = response.getBody();
            return (libros != null && !libros.isEmpty()) ? libros.get(0) : null;
        } catch (HttpClientErrorException e) {
            System.out.println("Error al buscar libro por título '" + titulo + "': " + e.getMessage());
            return null;
        }
    }
}
