package com.goosvandenbekerom.Resource;

import com.goosvandenbekerom.bean.MentionRepo;
import com.goosvandenbekerom.model.Kweet;
import com.goosvandenbekerom.model.Mention;
import com.goosvandenbekerom.model.User;
import com.goosvandenbekerom.util.HATEOAS;
import io.swagger.v3.oas.annotations.Operation;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

@Path("mention")
public class MentionResource extends JsonResource {
    private MentionRepo repo;

    @Inject
    public MentionResource(MentionRepo repo) { this.repo = repo; }

    @GET
    @Path("{id}")
    @Operation(summary = "Get a mention by its id")
    public Mention find(@PathParam("id") long id) {
        return HATEOAS.mention(repo.getById(id), uri);
    }

    @GET
    @Path("{id}/user")
    @Operation(summary = "Get the user that is mentioned in this kweet")
    public User getUser(@PathParam("id") long id) {
        return HATEOAS.user(repo.getUserFromMention(id), uri);
    }

    @GET
    @Path("{id}/kweet")
    @Operation(summary = "Get the kweet that this mention is in")
    public Kweet getKweet(@PathParam("id") long id) {
        return HATEOAS.kweet(repo.getKweetFromMention(id), uri);
    }
}
