package com.goosvandenbekerom.Resource;

import com.goosvandenbekerom.bean.MentionRepo;
import com.goosvandenbekerom.model.Kweet;
import com.goosvandenbekerom.model.Mention;
import io.swagger.v3.oas.annotations.Operation;

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
    @Operation(summary = "Get all mentions")
    public List<Mention> getAll() {
        return repo.getAll();
    }

    @GET
    @Path("{id}")
    @Operation(summary = "Get a mention by its id")
    public Mention find(@PathParam("id") long id) {
        return repo.getById(id);
    }

    @GET
    @Path("{id}/kweet")
    @Operation(summary = "Get the kweet associated with this mention")
    public Kweet getKweet(@PathParam("id") long id) {
        return repo.getKweetFromMention(id);
    }
}
