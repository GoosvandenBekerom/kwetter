package com.goosvandenbekerom.Resource;

import com.goosvandenbekerom.Exception.WrongUsernameOrPasswordException;
import com.goosvandenbekerom.annotation.Secured;
import com.goosvandenbekerom.bean.UserRepo;
import com.goosvandenbekerom.model.Credentials;
import com.goosvandenbekerom.model.Token;
import com.goosvandenbekerom.model.User;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.Context;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("user")
@Produces(APPLICATION_JSON)
public class UserResource extends Resource<User> {

    private final UserRepo repo;

    @Inject
    public UserResource(UserRepo repo) {
        super();
        this.repo = repo;
        super.setRepository(this.repo);
    }

    @POST
    @Path("login")
    @Consumes(APPLICATION_JSON)
    public Token login(Credentials credentials) throws WrongUsernameOrPasswordException {
        return repo.login(credentials);
    }

    @POST
    @Path("follow")
    @Consumes(APPLICATION_JSON)
    @Secured
    public boolean followUser(@Context ContainerRequestContext context, User user) {
        return repo.followUser(context.getProperty("user").toString(), user);
    }
}
