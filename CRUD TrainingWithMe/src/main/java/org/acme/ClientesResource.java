package org.acme;

import java.util.List;
import java.util.UUID;

import org.acme.entity.Cliente;
import org.acme.service.ClienteService;

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

@Path("/clientes")
public class ClientesResource {

    @Inject
    ClienteService clienteService;

    @Path("/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Cliente get(@PathParam("id") UUID id) {
        return clienteService.buscarPorId(id);
    }


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Cliente> list() {
        return clienteService.buscarTodos();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public List<Cliente> add(@Valid Cliente cliente) {
        clienteService.novoCliente(cliente);
        return clienteService.buscarTodos();
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    public List<Cliente> update(@Valid Cliente cliente) {
        clienteService.atualizar(cliente);
        return clienteService.buscarTodos();
    }

    @Path("/{id}")
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public List<Cliente> delete(@PathParam("id") UUID id) {
        clienteService.remover(id);
        return clienteService.buscarTodos();
    }
}