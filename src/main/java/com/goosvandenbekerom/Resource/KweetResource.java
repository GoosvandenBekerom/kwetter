package com.goosvandenbekerom.Resource;

import com.goosvandenbekerom.Exception.UnauthorizedException;
import com.goosvandenbekerom.annotation.Secured;
import com.goosvandenbekerom.bean.KweetRepo;
import com.goosvandenbekerom.bean.UserRepo;
import com.goosvandenbekerom.model.Kweet;
import com.goosvandenbekerom.model.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
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
    @Operation(summary = "Get all kweets")
    public List<Kweet> getAll() {
        return kweetRepo.getAll();
    }

    @GET
    @Path("{id}")
    @Operation(summary = "Get a kweet by its id")
    public Kweet find(@PathParam("id") long id) {
        return kweetRepo.getById(id);
    }

    @POST
    @Consumes(APPLICATION_FORM_URLENCODED)
    @Secured
    @Operation(summary = "Send a new kweet", security = @SecurityRequirement(name = "Bearer token"))
    public Kweet save(@Context ContainerRequestContext context, @FormParam("message") String message) {
        User user = userRepo.getById(context.getProperty("user").toString());
        Kweet kweet = new Kweet(user, message);
        return kweetRepo.save(kweet);
    }

    @PUT
    @Path("{id}/like")
    @Secured
    @Operation(summary = "Like a kweet", security = @SecurityRequirement(name = "Bearer token"))
    public Response toggleLike(@Context ContainerRequestContext context, @PathParam("id") long id) {
        kweetRepo.toggleLike(id, context.getProperty("user").toString());
        return Response.ok().build();
    }

    @GET
    @Path("{id}/likes")
    @Operation(summary = "Get a list of all users following this kweet")
    public List<User> getLikesById(@PathParam("id") long id) {
        return kweetRepo.getLikesById(id);
    }

    @DELETE
    @Path("{id}")
    @Secured
    @Operation(summary = "Delete a kweet", security = @SecurityRequirement(name = "Bearer token"))
    public Response delete(@Context ContainerRequestContext context, @PathParam("id") long id) {
        User user = userRepo.getById(context.getProperty("user").toString());
        Kweet kweet = kweetRepo.getById(id);
        if (!kweet.getOwner().equals(user)) {
            throw new UnauthorizedException();
        }
        kweetRepo.delete(kweet);
        return Response.ok().build();
    }
}