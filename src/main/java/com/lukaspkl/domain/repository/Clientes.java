package com.lukaspkl.domain.repository;


import com.lukaspkl.domain.entity.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import java.util.List;


@Repository
public class Clientes {



    @Autowired
    private EntityManager entityManager;

    @Transactional
    public Cliente salvar (Cliente cliente){
        entityManager.persist(cliente);
                return cliente;
    }
    @Transactional
    public Cliente atualizar (Cliente cliente){
        entityManager.merge(cliente);
        return cliente;
    }
    @Transactional
    public void deletar (Cliente cliente){
        if (!entityManager.contains(cliente)){
            cliente=entityManager.merge(cliente);
        }
        entityManager.remove(cliente);

    }
     @Transactional
     public List <Cliente> buscarPorNome (String nome){
        String jpql = "select c from Cliente c where c.nome = :nome";
         TypedQuery<Cliente> query = entityManager.createQuery(jpql, Cliente.class);
         query.setParameter("nome", "%"+ nome + "%");
          return query.getResultList();
      }
    @Transactional
    public void deletar (Integer id){
        Cliente cliente = entityManager.find(Cliente.class,id);
        deletar(cliente);

    }
    @Transactional
    public List<Cliente> obtertodos (){
        return entityManager.createQuery("from Cliente", Cliente.class).getResultList();
    }




}
