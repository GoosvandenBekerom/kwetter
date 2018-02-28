package com.goosvandenbekerom.Resource;

import com.goosvandenbekerom.annotation.Secured;
import com.goosvandenbekerom.bean.MentionRepo;
import com.goosvandenbekerom.model.Mention;

import javax.inject.Inject;
import javax.ws.rs.Path;

@Path("mention")
public class MentionResource extends Resource<Mention>{
    private final MentionRepo repo;

    @Inject
    public MentionResource(MentionRepo repo) {
        super();
        this.repo = repo;
        super.setRepository(this.repo);
    }

    @Override
    @Secured
    public Mention save(Mention entity) {
        return super.save(entity);
    }
}
