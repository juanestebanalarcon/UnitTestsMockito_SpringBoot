package com.jeam.springboottests.springbootunittests.controllers;

import com.jeam.springboottests.springbootunittests.models.Cuenta;
import com.jeam.springboottests.springbootunittests.models.TransaccionDto;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.core.AutoConfigureCache;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.*;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CuentaControllerWebClientTest {
    @Autowired
    private WebTestClient client;

    @Test
    @Order(1)
    void testTransferir() {
        TransaccionDto dto = new TransaccionDto();
        dto.setCuentaOrigenId(1L);
        dto.setBancoId(1L);
        dto.setCuentaDestinoId(2L);
        dto.setMonto(new BigDecimal("100"));

        client.post().uri("http://localhost/api/cuentas/transferir")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(dto)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.date").isEqualTo(LocalDate.now().toString())
                .jsonPath("$.Transaccion").isNotEmpty();

    }

    @Test
    @Order(2)
    void testListar() {
        client.get().uri("/api/cuentas").exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody()
                .jsonPath("$[0].persona").isEqualTo("Juan")
                .jsonPath("$[0].id").isEqualTo(1)
                .jsonPath("$[0].saldo").isEqualTo(900)
                .jsonPath("$[1].persona").isEqualTo("Jose")
                .jsonPath("$[1].id").isEqualTo(1)
                .jsonPath("$[1].saldo").isEqualTo(2100)
                .jsonPath("$").isArray()
                .jsonPath("$").value(hasSize(2));
        ;
    }
}    @Test
    @Order(3)
    void testListar2() {
        client.get().uri("/api/cuentas").exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBodyList(Cuenta.class)
                .consumeWith(resp->{
                    List<Cuenta>cuentas = resp.getResponseBody();
                    assertEquals(2,cuentas.size());
                    assertEquals("Juan",cuentas.get(0).getPersona());
                    assertEquals(1L,cuentas.get(0).getId());
                    assertEquals(900.00,cuentas.get(0).getSaldo().toPlainString());
                }).hasSize(1)
                .value(hasSize(2));
        ;
    }
}