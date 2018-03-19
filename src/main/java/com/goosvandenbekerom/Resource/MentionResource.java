package com.goosvandenbekerom.Resource;

import com.goosvandenbekerom.annotation.Secured;
import com.goosvandenbekerom.bean.MentionRepo;
import com.goosvandenbekerom.model.Kweet;
import com.goosvandenbekerom.model.Mention;
import com.goosvandenbekerom.model.User;
import com.goosvandenbekerom.util.HATEOAS;
import io.swagger.v3.oas.annotations.Operation;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("mention")
public class MentionResource extends JsonResource {
    private MentionRepo repo;

    @Inject
    public MentionResource(MentionRepo repo) {this.repo = repo;}

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

    @GET
    @Path("unseen")
    @Secured
    @Operation(summary = "Get all unseen mentions for the logged in User")
    public List<Mention> getUnseen(@Context ContainerRequestContext context) {
        return HATEOAS.mentionList(repo.getUnseen(context.getProperty("user").toString()), uri);
    }

    @PUT
    @Path("seen")
    @Operation(summary = "Set a mention's seen property to true")
    @Secured
    public Response setSeen(@Context ContainerRequestContext context, @FormParam("id") long id) {
        repo.setSeen(id, context.getProperty("user").toString());
        return Response.ok().build();
    }
}
