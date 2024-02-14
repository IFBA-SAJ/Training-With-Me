package org.acme.service;

import java.util.List;
import java.util.UUID;

import org.acme.entity.Exercicio;
import org.acme.repository.ExercicioRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@ApplicationScoped
public class ExercicioService {
    @Inject
    ExercicioRepository  exercicioRepository; 

    @Transactional 
    public void novoExercicio(@Valid Exercicio exercicio) {
        exercicioRepository.persist(exercicio);
    }
    
    public Exercicio buscarPorId(UUID id) {
        return exercicioRepository.findById(id);
    }

    public List<Exercicio> buscarTodos() {
        return exercicioRepository.listarTodosOrdenado();
    }
    
    @Transactional 
    public void atualizar(@Valid Exercicio exercicio) {
        EntityManager manager = exercicioRepository.getEntityManager();
        manager.merge(exercicio);
    }

    @Transactional 
    public void remover(UUID id) {
        exercicioRepository.deleteById(id);
    }
}