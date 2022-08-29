package com.jeam.springboottests.springbootunittests.controllers;

import com.jeam.springboottests.springbootunittests.models.Cuenta;
import com.jeam.springboottests.springbootunittests.models.TransaccionDto;
import com.jeam.springboottests.springbootunittests.services.ICuentaService;
import org.springframework.beans.factory.annotation.Autowired;
import static org.springframework.http.HttpStatus.*;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@RestController
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
    public ResponseEntity<?>transferir(@RequestBody TransaccionDto t){
        Map<String,Object> response = new HashMap<>();
        cuentaService.transferir(t.getCuentaOrigenId(),t.getBancoId(),t.getCuentaDestinoId(),t.getMonto());
        response.put("date", LocalDate.now());
        response.put("status", 200);
        response.put("transacción", t);

        return ResponseEntity.ok(response);

    }
    @DeleteMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    public void deleteById(@PathVariable Long id){
        cuentaService.deleteById(id);
    }

}
