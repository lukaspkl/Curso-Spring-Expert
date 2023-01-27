package com.lukaspkl.service.impl;


import com.lukaspkl.domain.entity.Cliente;
import com.lukaspkl.domain.entity.ItemPedido;
import com.lukaspkl.domain.entity.Pedido;
import com.lukaspkl.domain.entity.Produto;
import com.lukaspkl.domain.repository.Clientes;
import com.lukaspkl.domain.repository.ItemsPedido;
import com.lukaspkl.domain.repository.Pedidos;
import com.lukaspkl.domain.repository.Produtos;
import com.lukaspkl.exception.RegraNegocioException;
import com.lukaspkl.rest.dto.ItemPedidoDTO;
import com.lukaspkl.rest.dto.PedidoDTO;
import com.lukaspkl.service.PedidoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PedidoServiceImpl  implements PedidoService {

    private final Pedidos repository;
    private final Clientes clientesRepository;
    private final Produtos produtosRepository;
    private final ItemsPedido itemsPedidoRepository;



    @Override
    @Transactional
    public Pedido salvar( PedidoDTO dto) {
        Integer idCliente = dto.getCliente();
       Cliente cliente = clientesRepository
                .findById(idCliente)
                .orElseThrow(()-> new RegraNegocioException(
                        "Codigo de Cliente Inválido"));




        Pedido pedido = new Pedido();
        pedido.setTotal(dto.getTotal());
        pedido.setDataPedido(LocalDate.now());
        pedido.setCliente(cliente);

        List<ItemPedido> itemsPedido = converterItems(pedido, dto.getItems());
        repository.save(pedido);
        itemsPedidoRepository.saveAll(itemsPedido);
        pedido.setItens(itemsPedido);

        return pedido;

    }


    private List<ItemPedido> converterItems (Pedido pedido,  List<ItemPedidoDTO> items ){
        if (items.isEmpty()){
            throw  new RegraNegocioException("Nao e possivel realizar o pedido sem items.");
        }

        return items
                .stream()
                .map(dto ->{
                    Integer idProduto = dto.getProduto();
                    Produto produto =  produtosRepository
                            .findById(idProduto)
                            .orElseThrow(
                                    ()-> new RegraNegocioException(
                                            "Código de Produto Inválido:" + idProduto
                                    ));


                    ItemPedido itemPedido = new ItemPedido();
                    itemPedido.setQuantidade(dto.getQuantidade());
                    itemPedido.setPedido(pedido);
                    itemPedido.setProduto(produto);
                    return itemPedido;
                }).collect(Collectors.toList());

    }
}


