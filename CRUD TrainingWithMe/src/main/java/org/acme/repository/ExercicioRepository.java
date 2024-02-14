package org.acme.repository;

import java.util.List;
import java.util.UUID;

import org.acme.entity.Exercicio;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ExercicioRepository implements PanacheRepositoryBase<Exercicio, UUID> {

    public List<Exercicio> listarTodosOrdenado(){
        find("ORDER BY upper(tipo)").list();
        return find("ORDER BY upper(descricao)").list();
    }

}