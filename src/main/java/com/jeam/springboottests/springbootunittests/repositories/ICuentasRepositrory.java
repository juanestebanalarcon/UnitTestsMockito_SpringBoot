package com.jeam.springboottests.springbootunittests.repositories;

import com.jeam.springboottests.springbootunittests.models.Cuenta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ICuentasRepositrory extends JpaRepository<Cuenta,Long> {
//    List<Cuenta>findAll();
//    Cuenta findById(Long Id);
//    void Update(Cuenta cuenta);
    @Query("select c from Cuenta c where c.persona=?1")
    Cuenta findByPersona(String persona);
}
