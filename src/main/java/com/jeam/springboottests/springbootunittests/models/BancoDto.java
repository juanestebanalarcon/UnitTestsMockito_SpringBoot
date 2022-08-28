package com.jeam.springboottests.springbootunittests.models;

import lombok.Data;

import java.io.Serializable;

@Data
public class BancoDto implements Serializable {
    private final String nombre;
    private final int totalTransferencias;
}
