package com.jeam.springboottests.springbootunittests.services;

import com.jeam.springboottests.springbootunittests.models.Banco;
import com.jeam.springboottests.springbootunittests.models.Cuenta;
import com.jeam.springboottests.springbootunittests.repositories.IBancoRepository;
import com.jeam.springboottests.springbootunittests.repositories.ICuentasRepositrory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    @Transactional(readOnly = true)
    public Cuenta findById(Long Id) {
        return cuentasRepositrory.findById(Id).orElseThrow();
    }

    @Override
    @Transactional(readOnly = true)
    public int RevisarTotalTransferencia(Long BancoId) {
        Banco banco = bancoRepository.findById(BancoId);

        return banco.getTotalTransferencias();
    }

    @Override
    @Transactional(readOnly = true)
    public BigDecimal revisarSaldo(Long cuentaId) {
        Cuenta cuenta = cuentasRepositrory.findById(cuentaId).orElseThrow();

        return cuenta.getSaldo();
    }

    @Override
    @Transactional
    public void transferir(Long origen, Long bancoId, Long destino, BigDecimal monto) {

        Banco banco = bancoRepository.findById(bancoId);
        int totalTransferencias = banco.getTotalTransferencias();
        banco.setTotalTransferencias(++totalTransferencias);
        bancoRepository.Update(banco);
        Cuenta cuentaOrigen = cuentasRepositrory.findById(origen).orElseThrow();
        cuentaOrigen.debito(monto);
        cuentasRepositrory.save(cuentaOrigen);
        Cuenta cuentaDestino = cuentasRepositrory.findById(destino).orElseThrow();
        cuentaDestino.credito(monto);
        cuentasRepositrory.save(cuentaDestino);
    }

    @Override
    public void deleteById(Long id) {
        cuentasRepositrory.deleteById(id);
    }

}
