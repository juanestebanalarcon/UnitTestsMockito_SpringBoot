package com.jeam.springboottests.springbootunittests.models;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class TransaccionDto {
private Long cuentaOrigenId, bancoId, cuentaDestinoId;
private BigDecimal monto;

}
