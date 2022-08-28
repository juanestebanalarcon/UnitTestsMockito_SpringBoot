package com.jeam.springboottests.springbootunittests.services;

import com.jeam.springboottests.springbootunittests.models.Banco;
import com.jeam.springboottests.springbootunittests.models.Cuenta;
import com.jeam.springboottests.springbootunittests.repositories.IBancoRepository;
import com.jeam.springboottests.springbootunittests.repositories.ICuentasRepositrory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class CuentaService implements ICuentaService{
    private ICuentasRepositrory cuentasRepositrory;
    private IBancoRepository bancoRepository;

    public CuentaService(ICuentasRepositrory cuentasRepositrory, IBancoRepository bancoRepository) {
        this.cuentasRepositrory = cuentasRepositrory;
        this.bancoRepository = bancoRepository;
    }

    @Override
    public Cuenta findById(Long Id) {
        return cuentasRepositrory.findById(Id);
    }

    @Override
    public int RevisarTotalTransferencia(Long BancoId) {
        Banco banco = bancoRepository.findById(BancoId);

        return banco.getTotalTransferencias();
    }

    @Override
    public BigDecimal revisarSaldo(Long cuentaId) {
        Cuenta cuenta = cuentasRepositrory.findById(cuentaId);

        return cuenta.getSaldo();
    }

    @Override
    public void transferir(Long origen, Long bancoId, Long destino, BigDecimal monto) {

        Banco banco = bancoRepository.findById(bancoId);
        int totalTransferencias = banco.getTotalTransferencias();
        banco.setTotalTransferencias(++totalTransferencias);
        bancoRepository.Update(banco);
        Cuenta cuentaOrigen = cuentasRepositrory.findById(origen);
        cuentaOrigen.debito(monto);
        cuentasRepositrory.Update(cuentaOrigen);
        Cuenta cuentaDestino = cuentasRepositrory.findById(destino);
        cuentaDestino.credito(monto);
        cuentasRepositrory.Update(cuentaDestino);
    }

}
