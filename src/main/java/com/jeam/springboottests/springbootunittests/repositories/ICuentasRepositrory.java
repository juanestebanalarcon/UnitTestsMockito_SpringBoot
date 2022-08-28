package com.jeam.springboottests.springbootunittests.repositories;

import com.jeam.springboottests.springbootunittests.models.Cuenta;

import java.util.List;

public interface ICuentasRepositrory {
    List<Cuenta>findAll();
    Cuenta findById(Long Id);
    void Update(Cuenta cuenta);

}
