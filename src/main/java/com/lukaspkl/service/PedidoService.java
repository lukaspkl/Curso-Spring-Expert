package com.lukaspkl.service;

import com.lukaspkl.domain.entity.Pedido;
import com.lukaspkl.rest.dto.PedidoDTO;

public interface PedidoService {

    Pedido salvar (PedidoDTO dto);
}
