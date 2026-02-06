package org.example.controller;


import org.example.model.Losjojos;
import org.example.model.Saga;
import org.example.service.LosJoJosService;
import org.example.service.SagaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(RestLosJoJos.MAPPING)
public class RestLosJoJos {
    public static final String MAPPING = "/Mongo/losjojos";

    @Autowired
    private LosJoJosService sagaService;


    @GetMapping
    public List<Losjojos> getAll() {
        return sagaService.findAll();
    }

    @PostMapping
    public ResponseEntity<Losjojos> create(@RequestBody Losjojos saga) {
        Losjojos gardado = sagaService.save(saga);
        return ResponseEntity.ok(gardado);
    }
}