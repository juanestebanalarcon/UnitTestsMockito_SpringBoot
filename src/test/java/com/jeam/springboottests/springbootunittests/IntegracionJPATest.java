package com.jeam.springboottests.springbootunittests;

import com.jeam.springboottests.springbootunittests.models.Cuenta;
import com.jeam.springboottests.springbootunittests.repositories.ICuentasRepositrory;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class IntegracionJPATest {

    @Autowired
    ICuentasRepositrory cuentasRepositrory;

    @Test
    void findById() {
        Optional<Cuenta>cuenta = cuentasRepositrory.findById(1L);
        assertTrue(cuenta.isPresent());
        assertThrows(NoSuchElementException.class,cuenta::orElseThrow);



        ));
        assertEquals("Juan",cuenta.orElseThrow().getPersona());
        assertEquals("1000.00",cuenta.orElseThrow().getSaldo().toPlainString());

    }

    @Test
    void testFindAll() {
        List<Cuenta>cuentas = cuentasRepositrory.findAll();
        assertFalse(cuentas.isEmpty());
        assertEquals(2,cuentas.size());
    }

    @Test
    void testSave() {
        Cuenta c = new Cuenta(null,"Gabo", new BigDecimal("3000"));
        cuentasRepositrory.save(c);
        Cuenta cu = cuentasRepositrory.findByPersona("Gabo");
        assertEquals("Gabo",cu.getPersona());
        assertEquals(3,cu.getId());




    }
}
