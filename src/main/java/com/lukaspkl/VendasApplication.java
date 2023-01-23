package com.lukaspkl;

import com.lukaspkl.domain.entity.Cliente;
import com.lukaspkl.domain.repository.Clientes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class VendasApplication {

    @Bean
    public CommandLineRunner commandLineRunner (@Autowired Clientes clientes){
        return args -> {
            Cliente c = new Cliente (null, "lucas");
            clientes.save(c);

        };
    }

    public static void main(String[] args) {
        SpringApplication.run(VendasApplication.class, args);

    }
}
