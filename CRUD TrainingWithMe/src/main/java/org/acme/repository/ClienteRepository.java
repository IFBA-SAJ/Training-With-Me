package org.acme.repository;

import java.util.List;
import java.util.UUID;

import org.acme.entity.Cliente;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ClienteRepository implements PanacheRepositoryBase<Cliente, UUID> {

    public List<Cliente> listarTodosOrdenado(){
        return find("ORDER BY upper(nome)").list();
    }

}