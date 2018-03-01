package com.goosvandenbekerom.Resource;

import com.goosvandenbekerom.annotation.Secured;
import com.goosvandenbekerom.bean.KweetRepo;
import com.goosvandenbekerom.bean.UserRepo;
import com.goosvandenbekerom.model.Kweet;
import com.goosvandenbekerom.model.User;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.Context;
import java.util.List;

import static javax.ws.rs.core.MediaType.APPLICATION_FORM_URLENCODED;

@Path("kweet")
public class KweetResource extends JsonResource {
    private KweetRepo kweetRepo;
    private UserRepo userRepo;

    @Inject
    public KweetResource(KweetRepo kweetRepo, UserRepo userRepo) {
        this.kweetRepo = kweetRepo;
        this.userRepo = userRepo;
    }

    @GET
    public List<Kweet> getAll() {
        return kweetRepo.getAll();
    }

    @GET
    @Path("{id}")
    public Kweet find(@PathParam("id") long id) {
        return kweetRepo.getById(id);
    }

    @POST
    @Consumes(APPLICATION_FORM_URLENCODED)
    @Secured
    public Kweet save(@Context ContainerRequestContext context, @FormParam("message") String message) {
        User user = userRepo.getById(context.getProperty("user").toString());
        Kweet kweet = new Kweet(user, message);
        return kweetRepo.save(kweet);
    }
}