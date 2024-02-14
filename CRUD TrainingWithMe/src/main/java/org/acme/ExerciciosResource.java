package org.acme;

import java.util.List;
import java.util.UUID;

import org.acme.entity.Exercicio;
import org.acme.service.ExercicioService;

import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/exercicios")
public class ExerciciosResource {

    @Inject
    ExercicioService exercicioService;

    @Path("/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Exercicio get(@PathParam("id") UUID id) {
        return exercicioService.buscarPorId(id);
    }


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Exercicio> list() {
        return exercicioService.buscarTodos();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public List<Exercicio> add(@Valid Exercicio exercicio) {
        exercicioService.novoExercicio(exercicio);
        return exercicioService.buscarTodos();
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    public List<Exercicio> update(@Valid Exercicio exercicio) {
        exercicioService.atualizar(exercicio);
        return exercicioService.buscarTodos();
    }

    @Path("/{id}")
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public List<Exercicio> delete(@PathParam("id") UUID id) {
        exercicioService.remover(id);
        return exercicioService.buscarTodos();
    }
}