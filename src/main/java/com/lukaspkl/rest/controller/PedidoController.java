package com.lukaspkl.rest.controller;


import com.lukaspkl.domain.entity.Pedido;
import com.lukaspkl.rest.dto.PedidoDTO;
import com.lukaspkl.service.PedidoService;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    private PedidoService service;

    public PedidoController(PedidoService service)  {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public Integer save (@RequestBody PedidoDTO dto){
        Pedido pedido = service.salvar(dto);
        return  pedido.getId();

    }
}


