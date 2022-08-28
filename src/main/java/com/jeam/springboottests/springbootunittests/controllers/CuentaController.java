package com.jeam.springboottests.springbootunittests.controllers;

import com.jeam.springboottests.springbootunittests.models.Cuenta;
import com.jeam.springboottests.springbootunittests.services.ICuentaService;
import org.springframework.beans.factory.annotation.Autowired;
import static org.springframework.http.HttpStatus.*;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/cuentas")
public class CuentaController {
    @Autowired
    private ICuentaService cuentaService;

    @GetMapping("/{id}")
    @ResponseStatus(OK)
    public Cuenta detalle(@PathVariable Long Id){
        return cuentaService.findById(Id);
    }
    @PostMapping("/transferir")
    public ResponseEntity<?>transferir(){

    }


}
