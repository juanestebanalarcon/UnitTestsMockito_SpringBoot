package com.jeam.springboottests.springbootunittests.models;

import com.jeam.springboottests.springbootunittests.exceptions.DineroInsuficienteException;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;
import java.util.zip.DataFormatException;

@Getter
@Setter
@Entity
@Table(name="cuentas")
public class Cuenta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String persona;
    private BigDecimal saldo;

    public Cuenta(Long id, String persona, BigDecimal saldo) {
        Id = id;
        this.persona = persona;
        this.saldo = saldo;
    }

    public Cuenta() {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cuenta)) return false;
        Cuenta cuenta = (Cuenta) o;
        return Objects.equals(getId(), cuenta.getId()) && Objects.equals(getPersona(), cuenta.getPersona()) && Objects.equals(getSaldo(), cuenta.getSaldo());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getPersona(), getSaldo());
    }
    public void credito(BigDecimal monto){
        BigDecimal nuevoSaldo = this.saldo.subtract(monto);
        if(nuevoSaldo.compareTo(BigDecimal.ZERO)<0) {
            throw new
                    DineroInsuficienteException("Dinero insuficiente en la cuenta");
        }
        this.saldo = nuevoSaldo;
    }
    public void debito(BigDecimal monto){
        this.saldo = this.saldo.add(monto);

    }
}
