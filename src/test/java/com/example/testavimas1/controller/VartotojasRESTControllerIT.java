package com.example.testavimas1.controller;

import com.example.testavimas1.Testavimas1Application;
import com.example.testavimas1.model.Vartotojas;
import com.example.testavimas1.service.VartotojasService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.List;

@SpringBootTest(classes = Testavimas1Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class VartotojasRESTControllerIT {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private VartotojasService service;

    private Vartotojas vartotojas;

    @BeforeEach
    void setUp() {
        List<Vartotojas> vartotojai = (List<Vartotojas>)service.findAll();
        vartotojas = vartotojai.get(0);
    }

    @Test
    void test() {
        System.out.println("PORT=" + port);
    }

    @Test
    public void testGetVartotojasById() {
        String expected = "{\"id\":" + vartotojas.getId() + ", \"vardas\":\"" + vartotojas.getVardas() + "\", \"telNr\":\"" + vartotojas.getTelNr() +"\"}";

        WebTestClient
                .bindToServer()
                .baseUrl("http://localhost:" + port)
                .build()
                .get()
                .uri("/vartotojas-json/" + vartotojas.getId())
                .exchange()
                .expectStatus().isOk()
                .expectHeader().valueEquals("Content-Type", "application/json")
                .expectBody().json(expected);
    }


}
