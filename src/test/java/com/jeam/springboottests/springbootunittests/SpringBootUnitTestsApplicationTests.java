package com.jeam.springboottests.springbootunittests;

import com.jeam.springboottests.springbootunittests.models.Cuenta;
import com.jeam.springboottests.springbootunittests.repositories.IBancoRepository;
import com.jeam.springboottests.springbootunittests.repositories.ICuentasRepositrory;
import com.jeam.springboottests.springbootunittests.services.CuentaService;
import com.jeam.springboottests.springbootunittests.services.ICuentaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class SpringBootUnitTestsApplicationTests {

    @Mock
    ICuentasRepositrory cuentasRepositrory;
    @Mock
    IBancoRepository bancoRepository;
//    @InjectMocks
    @Autowired
    ICuentaService cuentaService;

    @BeforeEach
    void setUp() {
//        cuentasRepositrory = mock(ICuentasRepositrory.class);
//        bancoRepository = mock(IBancoRepository.class);
//        cuentaService = new CuentaService(cuentasRepositrory,bancoRepository);

    }

    @Test
    void contextLoads() {
    when(cuentasRepositrory.findById(1L)).thenReturn(Datos.CUENTA_001);
    when(cuentasRepositrory.findById(2L)).thenReturn(Datos.CUENTA_002);
    when(bancoRepository.findById(1L)).thenReturn(Datos.BANCO);
    BigDecimal saldoOrigen = cuentaService.revisarSaldo(1L);
    BigDecimal saldoDestino = cuentaService.revisarSaldo(2L);
    assertEquals("1000",saldoOrigen.toPlainString());
    assertEquals("2000",saldoDestino.toPlainString());
    cuentaService.transferir(1L,1L,2L,new BigDecimal("100"));
    assertEquals("900",saldoOrigen.toPlainString());
    assertEquals("2100",saldoDestino.toPlainString());
    verify(cuentasRepositrory).findById(1L);
    verify(cuentasRepositrory).findById(2L);
    verify(cuentasRepositrory,times(2)).findById(2L);
    verify(cuentasRepositrory,times(2)).Update(any(Cuenta.class));

    }
    @Test
    void contextLoads2() {
    when(cuentasRepositrory.findById(1L)).thenReturn(Datos.CUENTA_001);
    when(cuentasRepositrory.findById(2L)).thenReturn(Datos.CUENTA_002);
    when(bancoRepository.findById(1L)).thenReturn(Datos.BANCO);
    BigDecimal saldoOrigen = cuentaService.revisarSaldo(1L);
    BigDecimal saldoDestino = cuentaService.revisarSaldo(2L);
    assertEquals("1000",saldoOrigen.toPlainString());
    assertEquals("2000",saldoDestino.toPlainString());



    cuentaService.transferir(1L,1L,2L,new BigDecimal("100"));
    assertEquals("900",saldoOrigen.toPlainString());
    assertEquals("2100",saldoDestino.toPlainString());
    verify(cuentasRepositrory).findById(1L);
    verify(cuentasRepositrory).findById(2L);
    verify(cuentasRepositrory,times(2)).findById(2L);
    verify(cuentasRepositrory,times(2)).Update(any(Cuenta.class));

    }

    @Test
    void contextLoads3() {
        when(cuentasRepositrory.findById(1L)).thenReturn(Datos.CUENTA_001);
        Cuenta cuenta1 = cuentaService.findById(1L);
        Cuenta cuenta2 = cuentaService.findById(2L);
        assertSame(cuenta1,cuenta2);
        assertTrue(cuenta1==cuenta2);
        assertEquals("Juan",cuenta1.getPersona());
        assertEquals("Juan",cuenta2.getPersona());
        verify(cuentasRepositrory,times(2)).findById(1L);

    }
}
