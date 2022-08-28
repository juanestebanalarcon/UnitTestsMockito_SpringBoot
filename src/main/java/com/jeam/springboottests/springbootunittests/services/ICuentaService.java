package com.jeam.springboottests.springbootunittests.services;

import com.jeam.springboottests.springbootunittests.models.Cuenta;

import java.math.BigDecimal;

public interface ICuentaService {
    Cuenta findById(Long Id);
    int RevisarTotalTransferencia(Long BancoId);
    BigDecimal revisarSaldo(Long cuentaId);
    void transferir(Long origen,Long bancoId,Long destino,BigDecimal monto);


}
