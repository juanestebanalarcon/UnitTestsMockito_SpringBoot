package com.jeam.springboottests.springbootunittests.repositories;

import com.jeam.springboottests.springbootunittests.models.Banco;

import java.util.List;

public interface IBancoRepository {
    List<Banco>findAll();
    Banco findById(Long Id);
    void Update(Banco banco);

}
