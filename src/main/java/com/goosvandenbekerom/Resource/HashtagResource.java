package com.goosvandenbekerom.Resource;

import com.goosvandenbekerom.annotation.Secured;
import com.goosvandenbekerom.bean.HashtagRepo;
import com.goosvandenbekerom.model.Hashtag;

import javax.inject.Inject;
import javax.ws.rs.Path;

@Path("hashtag")
public class HashtagResource extends Resource<Hashtag>{
    private final HashtagRepo repo;

    @Inject
    public HashtagResource(HashtagRepo repo) {
        super();
        this.repo = repo;
        super.setRepository(this.repo);
    }

    @Override
    @Secured
    public Hashtag save(Hashtag entity) {
        return super.save(entity);
    }
}
