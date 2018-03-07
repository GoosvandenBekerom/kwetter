package com.goosvandenbekerom.Resource;

import com.goosvandenbekerom.bean.MentionRepo;
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
        List<Mention> mentions = repo.getAll();
        mentions.forEach(this::hateoas);
        return mentions;
    }

    @GET
    @Path("{id}")
    @Operation(summary = "Get a mention by its id")
    public Mention find(@PathParam("id") long id) {
        Mention mention = repo.getById(id);
        return hateoas(mention);
    }

    private Mention hateoas(Mention mention) {
        addSelfLink(mention);
        addUserLink(mention);
        addKweetLink(mention);
        return mention;
    }

    private void addSelfLink(Mention mention) {
        mention.addLink(
                "Self",
                uri.getBaseUriBuilder().path(MentionResource.class).path(String.valueOf(mention.getId())).build()
        );
    }

    private void addUserLink(Mention mention) {
        mention.addLink(
                "User",
                uri.getBaseUriBuilder().path(UserResource.class).path(mention.getUser().getUsername()).build()
        );
    }

    private void addKweetLink(Mention mention) {
        mention.addLink(
                "Kweet",
                uri.getBaseUriBuilder().path(KweetResource.class).path(String.valueOf(mention.getKweet().getId())).build()
        );
    }
}
