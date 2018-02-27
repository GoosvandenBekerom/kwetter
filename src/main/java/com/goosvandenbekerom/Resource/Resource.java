package com.goosvandenbekerom.Resource;

import com.goosvandenbekerom.bean.Repository;

import javax.inject.Inject;
import javax.ws.rs.*;

import java.util.List;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Produces(APPLICATION_JSON)
abstract public class Resource<T> {
    private Repository<T> repo;
    void setRepository(Repository<T> repo) { this.repo = repo; }

    @GET
    public List<T> getAll() {
        return repo.getAll();
    }

    @GET
    @Path("{id}")
    public T find(@PathParam("id") String id) {
        return repo.getById(id);
    }

    @POST
    @Consumes(APPLICATION_JSON)
    public T save(T entity) {
        return repo.save(entity);
    }
}
