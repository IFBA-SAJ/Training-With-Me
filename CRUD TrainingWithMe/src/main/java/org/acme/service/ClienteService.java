package org.acme.service;

import java.util.List;
import java.util.UUID;

import org.acme.entity.Cliente;
import org.acme.repository.ClienteRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@ApplicationScoped
public class ClienteService {
    @Inject
    ClienteRepository  clienteRepository; 

    @Transactional 
    public void novoCliente(@Valid Cliente cliente) {
        clienteRepository.persist(cliente);
    }
    
    public Cliente buscarPorId(UUID id) {
        return clienteRepository.findById(id);
    }

    public List<Cliente> buscarTodos() {
        return clienteRepository.listarTodosOrdenado();
    }
    
    @Transactional 
    public void atualizar(@Valid Cliente cliente) {
        EntityManager manager = clienteRepository.getEntityManager();
        manager.merge(cliente);
    }

    @Transactional 
    public void remover(UUID id) {
        clienteRepository.deleteById(id);
    }
}