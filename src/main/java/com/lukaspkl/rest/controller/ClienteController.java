package com.lukaspkl.rest.controller;

import com.lukaspkl.domain.entity.Cliente;
import com.lukaspkl.domain.repository.Clientes;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping ("/api/clientes")
public class ClienteController {

    private Clientes repository;

    public ClienteController(Clientes clientes) {

        this.repository = clientes;
    }

    @GetMapping("{id}")

    public Cliente getclienteById (@PathVariable Integer id){
        return repository
                .findById(id)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente nao encontrado"));

    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente save (@RequestBody Cliente cliente){
        return repository.save(cliente);
    }

    @DeleteMapping ("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete (@PathVariable Integer id) {

        repository.findById(id)
                .map(cliente -> {
                    repository.delete(cliente);
                    return cliente;
                })
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND));


    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update (@PathVariable Integer id,
                        @RequestBody Cliente cliente){
         repository.findById(id)
                .map( clienteExistente -> {
                    cliente.setId(clienteExistente.getId());
                    repository.save(cliente);
                    return clienteExistente;
                }).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public List<Cliente> find (Cliente filtro){
        ExampleMatcher matcher = ExampleMatcher
                                    .matching()
                                    .withIgnoreCase()
                                    .withStringMatcher(
                                            ExampleMatcher.StringMatcher.CONTAINING);
        Example example = Example.of(filtro, matcher);
         return repository.findAll(example);
    }
}

