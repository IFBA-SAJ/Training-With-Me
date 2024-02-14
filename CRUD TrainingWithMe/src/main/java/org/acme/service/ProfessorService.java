package org.acme.service;

import java.util.List;
import java.util.UUID;

import org.acme.entity.Professor;
import org.acme.repository.ProfessorRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@ApplicationScoped
public class ProfessorService {
    @Inject
    ProfessorRepository  professorRepository; 

    @Transactional 
    public void novoProfessor(@Valid Professor professor) {
        professorRepository.persist(professor);
    }
    
    public Professor buscarPorId(UUID id) {
        return professorRepository.findById(id);
    }

    public List<Professor> buscarTodos() {
        return professorRepository.listarTodosOrdenado();
    }
    
    @Transactional 
    public void atualizar(@Valid Professor professor) {
        EntityManager manager = professorRepository.getEntityManager();
        manager.merge(professor);
    }

    @Transactional 
    public void remover(UUID id) {
        professorRepository.deleteById(id);
    }
}