package com.jeam.springboottests.springbootunittests.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name="bancos")
public class Banco {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String nombre;
    private int totalTransferencias;

    public Banco() {

    }

    public void Banco(){

    }

    public Banco(Long id, String nombre, int totalTransferencias) {
        Id = id;
        this.nombre = nombre;
        this.totalTransferencias = totalTransferencias;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Banco)) return false;
        Banco banco = (Banco) o;
        return getTotalTransferencias() == banco.getTotalTransferencias() && Objects.equals(getId(), banco.getId()) && Objects.equals(getNombre(), banco.getNombre());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getNombre(), getTotalTransferencias());
    }

}
