package com.goosvandenbekerom.Resource;

import com.goosvandenbekerom.annotation.Secured;
import com.goosvandenbekerom.bean.UserRepo;
import com.goosvandenbekerom.model.User;
import io.swagger.v3.oas.annotations.Operation;

import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.*;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import java.util.List;

import static javax.ws.rs.core.MediaType.APPLICATION_FORM_URLENCODED;

@Path("user")
public class UserResource extends JsonResource{
    private UserRepo repo;

    @Inject
    public UserResource(UserRepo repo) { this.repo = repo; }

    @GET
    @Operation(summary = "Get users", description = "Get list of users")
    public List<User> getAll() {
        return repo.getAll();
    }

    @GET
    @Path("{id}")
    public User find(@PathParam("id") String id) {
        return repo.getById(id);
    }

    @POST
    @Consumes(APPLICATION_FORM_URLENCODED)
    public User save(
            @FormParam("username") String username,
            @FormParam("password") String password,
            @FormParam("fullName") String fullName)
    {
        return repo.save(new User(username, password, fullName));
    }

    @POST
    @Path("login")
    @Consumes(APPLICATION_FORM_URLENCODED)
    public Response login(@FormParam("username") String username, @FormParam("password") String password) {
        String token = repo.login(username, password);
        JsonObject json = Json.createObjectBuilder().add("token", token).build();
        return Response.ok(json).build();
    }

    @POST
    @Path("{username}/follow")
    @Secured
    public Response followUser(@Context ContainerRequestContext context, @PathParam("username") String username) {
        repo.followUser(context.getProperty("user").toString(), username);
        JsonObject json = Json.createObjectBuilder().add("message", "Successfully followed user @" + username).build();
        return Response.ok(json).build();
    }

    @GET
    @Path("{username}/following")
    public List<User> getFollowing(@PathParam("username") String username) {
        return repo.getFollowing(username);
    }

    @GET
    @Path("{username}/followers")
    public List<User> getFollowers(@PathParam("username") String username) {
        return repo.getFollowers(username);
    }
}