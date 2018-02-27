package com.goosvandenbekerom.Resource;

import com.goosvandenbekerom.Exception.WrongUsernameOrPasswordException;
import com.goosvandenbekerom.annotation.Secured;
import com.goosvandenbekerom.bean.UserRepo;
import com.goosvandenbekerom.model.Credentials;
import com.goosvandenbekerom.model.Token;
import com.goosvandenbekerom.model.User;

import javax.inject.Inject;
import javax.ws.rs.*;

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
}
