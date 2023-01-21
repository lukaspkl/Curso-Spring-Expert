package com.lukaspkl;
import com.lukaspkl.domain.entity.Cliente;
import com.lukaspkl.domain.entity.Pedido;
import com.lukaspkl.domain.repository.Clientes;
import com.lukaspkl.domain.repository.Pedidos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;
import java.time.LocalDate;

@SpringBootApplication
public class VendasApplication {

    @Bean
    public CommandLineRunner init (
            @Autowired Clientes clientes,
            @Autowired Pedidos pedidos){
        return args -> {

            System.out.println("Salvando Clientes");
            clientes.save(new Cliente("Lucas"));
            Cliente lucas = new Cliente ("Lucas");
            clientes.save(lucas);

            Pedido p = new Pedido();
            p.setCliente(lucas);
            p.setDataPedido(LocalDate.now());
            p.setTotal(BigDecimal.valueOf(100));
            pedidos.save(p);

            Cliente cliente = clientes.findclientFecthPedidos(lucas.getId());
            System.out.println(cliente);
            System.out.println(cliente.getPedidos());

        };
    }

    public static void main(String[] args) {
        SpringApplication.run(VendasApplication.class, args);

    }
}
