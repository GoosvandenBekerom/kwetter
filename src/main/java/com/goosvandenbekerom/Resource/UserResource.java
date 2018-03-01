package com.goosvandenbekerom.Resource;

import com.goosvandenbekerom.annotation.Secured;
import com.goosvandenbekerom.bean.UserRepo;
import com.goosvandenbekerom.model.StringResponse;
import com.goosvandenbekerom.model.Token;
import com.goosvandenbekerom.model.User;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.Context;
import java.util.List;

import static javax.ws.rs.core.MediaType.APPLICATION_FORM_URLENCODED;

@Path("user")
public class UserResource extends JsonResource{
    private UserRepo repo;

    @Inject
    public UserResource(UserRepo repo) { this.repo = repo; }

    @GET
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
    public Token login(@FormParam("username") String username, @FormParam("password") String password) {
        return repo.login(username, password);
    }

    @POST
    @Path("{username}/follow")
    @Secured
    public StringResponse followUser(@Context ContainerRequestContext context, @PathParam("username") String username) {
        repo.followUser(context.getProperty("user").toString(), username);
        return new StringResponse("Successfully followed user @" + username);
    }
}