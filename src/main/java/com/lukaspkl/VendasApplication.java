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
            clientes.salvar(new Cliente("Lucas"));
            clientes.salvar(new Cliente("Rafa"));

            List<Cliente> todosclientes = clientes.obtertodos();
            todosclientes.forEach(System.out::println);



            System.out.println("Atualizando Clientes");
            todosclientes.forEach(c ->{
                c.setNome(c.getNome() + "Atualizado");
                clientes.atualizar(c);
            });
            System.out.println("Buscando Clientes");
            clientes.buscarPorNome("afa").forEach(System.out::println);


            System.out.println("Deletando Clientes");
            clientes.obtertodos().forEach(c ->{
                clientes.deletar(c);
            });
            todosclientes = clientes.obtertodos();
            if (todosclientes.isEmpty()) {
                System.out.println("Nenhum cliente na lista");
            }else {
              todosclientes.forEach(System.out::println);
            }

        };
    }


    public static void main(String[] args) {
        SpringApplication.run(VendasApplication.class, args);

    }
}
