package com.lukaspkl;


import com.lukaspkl.domain.entity.Cliente;
import com.lukaspkl.domain.repository.Clientes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;


@SpringBootApplication
public class VendasApplication {

    @Bean
    public CommandLineRunner init (@Autowired Clientes clientes){
        return args -> {

            System.out.println("Salvando Clientes");
            clientes.save(new Cliente("Lucas"));
            clientes.save(new Cliente("Rafa"));

            boolean existe = clientes.existsByNome("Lucas");
            System.out.println("Existe um cliente com o nome Lucas ?" + existe);




        };
    }


    public static void main(String[] args) {
        SpringApplication.run(VendasApplication.class, args);

    }
}
