package com.jeam.springboottests.springbootunittests;

import com.jeam.springboottests.springbootunittests.models.Banco;
import com.jeam.springboottests.springbootunittests.models.Cuenta;

import java.math.BigDecimal;

public class Datos {
    public static final Cuenta CUENTA_001 = new Cuenta(1L,"Juan",new BigDecimal("1000"));
    public static final Cuenta CUENTA_002 = new Cuenta(2L,"Jose",new BigDecimal("2000"));
    public static final Banco BANCO = new Banco(1L,"BBVA",0);

}
