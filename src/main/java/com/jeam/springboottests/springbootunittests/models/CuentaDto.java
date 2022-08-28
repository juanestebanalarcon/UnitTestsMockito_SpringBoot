package com.jeam.springboottests.springbootunittests.models;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class CuentaDto implements Serializable {
    private final String persona;
    private final BigDecimal saldo;
}
