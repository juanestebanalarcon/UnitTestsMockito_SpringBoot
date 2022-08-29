package com.jeam.springboottests.springbootunittests.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jeam.springboottests.springbootunittests.models.Cuenta;
import com.jeam.springboottests.springbootunittests.models.TransaccionDto;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CuentaControllerTestRestTemplate {

    @Autowired
    private TestRestTemplate client;

    private ObjectMapper mapper;

    @LocalServerPort
    private int port;

    @BeforeEach
    void setUp() {
        mapper = new ObjectMapper();
    }

    @Test
    @Order(1)
    void ListarTest() {
        TransaccionDto dto = new TransaccionDto();
        dto.setCuentaOrigenId(1L);
        dto.setBancoId(1L);
        dto.setCuentaDestinoId(2L);
        dto.setMonto(new BigDecimal("100"));

        ResponseEntity<String> resp =
        client.postForEntity("http://localhost:"+port+"/api/cuentas/transferir",dto,String.class);
        String json = resp.getBody();
        assertTrue(json.contains(LocalDate.now().toString()));
        assertNotNull(json);
        assertEquals(HttpStatus.OK,resp.getStatusCode());
        assertEquals(MediaType.APPLICATION_JSON,resp.getHeaders().getContentType());

    }

    @Test
    @Order(2)
    void testDetalle() {
        ResponseEntity<Cuenta>res = client.getForEntity("http://localhost:"+port+"/api/cuentas/1",Cuenta.class);
        Cuenta c = res.getBody();
        assertEquals(HttpStatus.OK,res.getStatusCode());
        assertEquals(MediaType.APPLICATION_JSON,res.getHeaders().getContentType());
        assertNotNull(c);
        assertEquals("Juan",c.getPersona());
        assertEquals("900.00",c.getSaldo());

    }
}