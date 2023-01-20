package com.lukaspkl.service;


import com.lukaspkl.model.Cliente;
import com.lukaspkl.repository.ClientesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientesServices {

    private ClientesRepository repository;

    @Autowired
    public ClientesServices(ClientesRepository repository){
        this.repository = repository;
    }

    public void salvarCliente (Cliente cliente){
        validarCliente(cliente);
        this.repository.persistir (cliente);
    }

    public void validarCliente (Cliente cliente){
        //aplica validaçoes
    }
}
