package com.goosvandenbekerom.Resource;

import com.goosvandenbekerom.bean.KweetRepo;
import com.goosvandenbekerom.model.Kweet;
import com.goosvandenbekerom.util.HATEOAS;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;

import javax.inject.Inject;
import javax.ws.rs.*;
import java.util.List;

@Path("timeline")
public class TimelineResource extends JsonResource {
    private KweetRepo kweetRepo;
    @Inject
    public TimelineResource(KweetRepo kweetRepo) { this.kweetRepo = kweetRepo; }

    @GET
    @Path("user/{username}")
    @Operation(summary = "Get the timeline for a user")
    public List<Kweet> getTimelineForUser(
            @PathParam("username") String username,
            @Parameter(description = "offset to start from") @QueryParam("offset") @DefaultValue("0") int offset,
            @Parameter(description = "maximum amount of kweets") @QueryParam("limit") @DefaultValue("50") int limit
    ) {
        if (offset < 0) offset = 0;
        if (limit < 0) limit = 50;
        return HATEOAS.kweetList(kweetRepo.getTimelineForUser(username, offset, limit), uri);
    }

    @GET
    @Path("hashtag/{hashtag}")
    @Operation(summary = "Get the timeline for a hashtag")
    public List<Kweet> getTimelineForHashtag(
            @PathParam("hashtag") String hashtag,
            @Parameter(description = "offset to start from") @QueryParam("offset") @DefaultValue("0") int offset,
            @Parameter(description = "maximum amount of kweets") @QueryParam("limit") @DefaultValue("50") int limit
    ) {
        if (offset < 0) offset = 0;
        if (limit < 0) limit = 50;
        return HATEOAS.kweetList(kweetRepo.getTimelineForHashtag(hashtag, offset, limit), uri);
    }
}
