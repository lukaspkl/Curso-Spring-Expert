package com.lukaspkl.service.impl;


import com.lukaspkl.domain.entity.Pedido;
import com.lukaspkl.domain.repository.Pedidos;
import com.lukaspkl.service.PedidoService;
import org.springframework.stereotype.Service;

@Service
public class PedidoServiceImpl  implements PedidoService {

    private Pedidos repository;

    public PedidoServiceImpl(Pedidos repository) {
        this.repository = repository;
    }
}


