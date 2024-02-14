package org.acme.repository;

import java.util.List;
import java.util.UUID;

import org.acme.entity.Professor;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ProfessorRepository implements PanacheRepositoryBase<Professor, UUID> {

    public List<Professor> listarTodosOrdenado(){
        return find("ORDER BY upper(cref)").list();
    }

}