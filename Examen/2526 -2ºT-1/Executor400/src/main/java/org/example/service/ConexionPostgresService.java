package org.example.service;

import org.example.model.Saga;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

@Service
public class ConexionPostgresService {

    @Autowired
    private RestTemplate restTemplate;


    private static final String POSTGRES_BASE_URL_SAGA = "http://localhost:8081/postgres/sagas";



    public List<Saga> buscarSagas() {
        try {
            String url = POSTGRES_BASE_URL_SAGA;
            ResponseEntity<List<Saga>> response = restTemplate.exchange(
                    url, HttpMethod.GET, null,
                    new ParameterizedTypeReference<List<Saga>>() {}
            );
            return response.getBody() != null ? response.getBody() : Collections.emptyList();
        } catch (HttpClientErrorException e) {
            System.out.println("Erro: " + e.getMessage());
            return Collections.emptyList();
        }
    }

    public boolean borrarSaga(Long id) {
        try {
            String url = POSTGRES_BASE_URL_SAGA+"/"+id;
            ResponseEntity<Void> response = restTemplate.exchange(
                    url, HttpMethod.DELETE, null, Void.class
            );
            return true;
        } catch (HttpClientErrorException e) {
            System.out.println("NonNonNon non dixeche-la palabra maxica jajaja jajaja " + e.getMessage());
            return false;
        }
    }

    public Saga crearSaga(Saga saga) {
        try {
            String url = POSTGRES_BASE_URL_SAGA;
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<Saga> request = new HttpEntity<>(saga, headers);

            ResponseEntity<Saga> response = restTemplate.exchange(
                    url, HttpMethod.POST, request, Saga.class
            );
            return response.getBody();
        } catch (HttpClientErrorException e) {
            System.out.println("Erro xenerico: " + e.getMessage());
            return null;
        }
    }


    public Saga sagaPorID(Long id) {
        try {
            String url = POSTGRES_BASE_URL_SAGA+"/"+id;
            HttpEntity<Saga> response = restTemplate.exchange(url, HttpMethod.GET, null, Saga.class);
            return response.getBody();
        } catch (HttpClientErrorException e) {
            System.out.println("Mensaxe xenerica " + e.getMessage());
            return null;
        }
    }

    public Saga sagaPorTitulo(String titulo) {
        try {
            String url = POSTGRES_BASE_URL_SAGA+"/titulo/"+titulo;
            HttpEntity<List<Saga>> response = restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<List<Saga>>() {});
            List<Saga> s = response.getBody();
            return s.get(0);
        } catch (HttpClientErrorException e) {
            System.out.println("Mensaxe xenerica " + e.getMessage());
            return null;
        }
    }

}
