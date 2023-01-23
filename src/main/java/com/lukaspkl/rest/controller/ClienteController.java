package com.lukaspkl.rest.controller;


import com.lukaspkl.domain.entity.Cliente;
import com.lukaspkl.domain.repository.Clientes;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class ClienteController {

    private Clientes clientes;

    public ClienteController(Clientes clientes) {
        this.clientes = clientes;
    }

    @GetMapping("/api/clientes/{id}")
    @ResponseBody
    public ResponseEntity getclienteById (@PathVariable Integer id){
        Optional<Cliente> cliente = clientes.findById(id);

        if (cliente.isPresent()){
            return ResponseEntity.ok(cliente.get());
        }

        return ResponseEntity.notFound().build();


    }




}
