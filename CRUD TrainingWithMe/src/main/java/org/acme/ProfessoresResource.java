package org.acme;

import java.util.List;
import java.util.UUID;

import org.acme.entity.Professor;
import org.acme.service.ProfessorService;

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

@Path("/professores")
public class ProfessoresResource {

    @Inject
    ProfessorService professorService;

    @Path("/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Professor get(@PathParam("id") UUID id) {
        return professorService.buscarPorId(id);
    }


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Professor> list() {
        return professorService.buscarTodos();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public List<Professor> add(@Valid Professor professor) {
        professorService.novoProfessor(professor);
        return professorService.buscarTodos();
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    public List<Professor> update(@Valid Professor professor) {
        professorService.atualizar(professor);
        return professorService.buscarTodos();
    }

    @Path("/{id}")
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public List<Professor> delete(@PathParam("id") UUID id) {
        professorService.remover(id);
        return professorService.buscarTodos();
    }
}