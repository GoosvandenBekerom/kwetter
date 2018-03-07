package com.goosvandenbekerom.Resource;

import com.goosvandenbekerom.bean.HashtagRepo;
import com.goosvandenbekerom.model.Hashtag;
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
        return repo.getAll();
    }

    @GET
    @Path("{id}")
    @Operation(summary = "Get a hashtag by its id")
    public Hashtag find(@PathParam("id") String id) {
        return repo.getById(id);
    }
}
