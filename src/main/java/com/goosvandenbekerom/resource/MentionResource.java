package com.goosvandenbekerom.resource;

import com.goosvandenbekerom.bean.MentionRepo;
import com.goosvandenbekerom.model.Mention;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import java.util.List;

@Path("mention")
public class MentionResource extends JsonResource {
    private MentionRepo repo;

    @Inject
    public MentionResource(MentionRepo repo) { this.repo = repo; }

    @GET
    public List<Mention> getAll() {
        return repo.getAll();
    }

    @GET
    @Path("{id}")
    public Mention find(@PathParam("id") long id) {
        return repo.getById(id);
    }
}
