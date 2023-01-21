package com.lukaspkl.domain.repository;


import com.lukaspkl.domain.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface  Clientes extends JpaRepository<Cliente, Integer> {

    List<Cliente> findByNomeLike(String nome);

    boolean existsByNome (String nome);

    @Query("select c from Cliente c left join fetch c.pedidos where c.id = :id")
    Cliente findclientFecthPedidos ( @Param("id") Integer id);
}
