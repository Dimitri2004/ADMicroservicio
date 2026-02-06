package org.example;

import org.example.model.Losjojos;
import org.example.model.Personaxe;
import org.example.model.Saga;
import org.example.service.ConexionMongoService;
import org.example.service.ConexionPostgresService;
import org.example.service.JSONService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class Secuencia {

    @Autowired
    private ConexionPostgresService conexionService;
    @Autowired
    private ConexionMongoService mongoService;
    @Autowired
    private JSONService JSONService;


    public void executar() {

        ArrayList<Personaxe> ps = new ArrayList<>();
        Personaxe p1 = new Personaxe();
        p1.setNome("Giorno Giovanna");
        p1.setStand("Gold Experience");
        ps.add(p1);

        Personaxe p2 = new Personaxe();
        p2.setNome("Bruno Bucciarati");
        p2.setStand("Sticky Fingers");
        ps.add(p2);

        Personaxe p3 = new Personaxe();
        p3.setNome("Guido Mista");
        p3.setStand("Sex Pistols");
        ps.add(p3);

        Saga saga = new Saga();
        saga.setTitulo("Vento Aureo");
        saga.setParte(5);
        saga.setAmbientacion("Italia");
        saga.setAnoinicio(2001);
        saga.setPersonaxes(ps);

        saga = conexionService.crearSaga(saga);


        Saga saga2 = conexionService.sagaPorID(2L);
        mongoService.crearSaga(saga2);

        Saga sagaC = conexionService.sagaPorTitulo("Stardust Crusaders");
        mongoService.crearSaga(sagaC);

        List<Saga> sagas = conexionService.buscarSagas();
        for (Saga s:sagas){
            mongoService.crearSaga(s);
        }

        Losjojos losjojos = new Losjojos();
        losjojos.setSagas(sagas);
        mongoService.crearLosjojos(losjojos);

        JSONService.exportarJSONLosJojos(mongoService.buscarJoJos());
        JSONService.exportarJSONSagass(mongoService.buscarSagas());

        conexionService.borrarSaga(saga.getId());
        conexionService.borrarSaga(saga.getId());

    }
}
