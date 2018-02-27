package com.goosvandenbekerom.Resource;

import com.goosvandenbekerom.bean.KweetRepo;
import com.goosvandenbekerom.model.Kweet;

import javax.inject.Inject;
import javax.ws.rs.Path;

@Path("kweet")
public class KweetResource extends Resource<Kweet> {
    private final KweetRepo repo;

    @Inject
    public KweetResource(KweetRepo repo) {
        super();
        this.repo = repo;
        super.setRepository(this.repo);
    }
}