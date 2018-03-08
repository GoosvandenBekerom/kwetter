package com.goosvandenbekerom.Resource;

import com.goosvandenbekerom.bean.HashtagRepo;
import com.goosvandenbekerom.model.Hashtag;
import com.goosvandenbekerom.model.Kweet;
import com.goosvandenbekerom.util.HATEOAS;
import io.swagger.v3.oas.annotations.Operation;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import java.util.List;

@Path("hashtag")
public class HashtagResource extends JsonResource {
    private HashtagRepo repo;

    @Inject
    public HashtagResource(HashtagRepo repo) { this.repo = repo; }

    @GET
    @Operation(summary = "Get all hashtags")
    public List<Hashtag> getAll() {
        return HATEOAS.hashtagList(repo.getAll(), uri);
    }

    @GET
    @Path("{tag}")
    @Operation(summary = "Get a hashtag by its id")
    public Hashtag find(@PathParam("tag") String tag) {
        return HATEOAS.hashtag(repo.getById(tag), uri);
    }

    @GET
    @Path("{tag}/kweets")
    @Operation(summary = "Get kweets that are tagged with this hashtag")
    public List<Kweet> getKweets(@PathParam("tag") String tag) {
        return HATEOAS.kweetList(repo.getKweetsWithTag(tag), uri);
    }
}
