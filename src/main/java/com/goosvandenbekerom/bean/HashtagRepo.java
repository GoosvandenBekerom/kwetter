package com.goosvandenbekerom.bean;

import com.goosvandenbekerom.model.Hashtag;
import com.goosvandenbekerom.service.HashtagService;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class HashtagRepo extends Repository<Hashtag> {
    @Inject
    private HashtagService hashtagService;

    public HashtagRepo() { super(Hashtag.class); }

    @Override
    public Hashtag save(Hashtag tag) {
        tag = hashtagService.create(tag.getValue());
        return super.save(tag);
    }
}
