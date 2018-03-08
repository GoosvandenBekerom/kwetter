package com.goosvandenbekerom.Resource;

import com.goosvandenbekerom.annotation.Secured;
import com.goosvandenbekerom.bean.UserRepo;
import com.goosvandenbekerom.model.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

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
    @Operation(summary = "Get all users")
    public List<User> getAll() {
        return hateoasList(repo.getAll());
    }

    @GET
    @Path("{username}")
    @Operation(summary = "Get user by username")
    public User find(@PathParam("username") String username) {
        return hateoas(repo.getById(username));
    }

    @POST
    @Consumes(APPLICATION_FORM_URLENCODED)
    @Operation(summary = "Register a new user")
    public User save(
            @FormParam("username") String username,
            @FormParam("password") String password,
            @FormParam("fullName") String fullName)
    {
        User user = repo.save(new User(username, password, fullName));
        return hateoas(user);
    }

    @POST
    @Path("login")
    @Consumes(APPLICATION_FORM_URLENCODED)
    @Operation(summary = "Login user", responses = @ApiResponse(description = "Authorisation token"))
    public Response login(@FormParam("username") String username, @FormParam("password") String password) {
        String token = repo.login(username, password);
        JsonObject json = Json.createObjectBuilder().add("token", token).build();
        return Response.ok(json).build();
    }

    @POST
    @Path("{username}/follow")
    @Secured
    @Operation(summary = "Follow user", security = @SecurityRequirement(name = "Bearer token"))
    public Response followUser(@Context ContainerRequestContext context, @PathParam("username") String username) {
        repo.followUser(context.getProperty("user").toString(), username);
        JsonObject json = Json.createObjectBuilder().add("message", "Successfully followed user @" + username).build();
        return Response.ok(json).build();
    }

    @GET
    @Path("{username}/following")
    @Operation(summary = "Get all users that this user follows")
    public List<User> getFollowing(@PathParam("username") String username) {
        return hateoasList(repo.getFollowing(username));
    }

    @GET
    @Path("{username}/followers")
    @Operation(summary = "Get all followers for this user")
    public List<User> getFollowers(@PathParam("username") String username) {
        return hateoasList(repo.getFollowers(username));
    }

    private User hateoas(User user) {
        addSelfLink(user);
        addFollowingLink(user);
        addFollowersLink(user);
        return user;
    }

    private List<User> hateoasList(List<User> users) {
        users.forEach(this::hateoas);
        return users;
    }

    private void addSelfLink(User user) {
        user.addLink(
                "Self",
                uri.getBaseUriBuilder().path(getClass()).path(user.getUsername()).build()
        );
    }

    private void addFollowingLink(User user) {
        user.addLink(
                "Following",
                uri.getBaseUriBuilder().path(getClass()).path(user.getUsername()).path("following").build()
        );
    }

    private void addFollowersLink(User user) {
        user.addLink(
                "Following",
                uri.getBaseUriBuilder().path(getClass()).path(user.getUsername()).path("followers").build()
        );
    }
}