package com.goosvandenbekerom.bean;

import com.goosvandenbekerom.model.Hashtag;
import com.goosvandenbekerom.model.Kweet;
import com.goosvandenbekerom.service.HashtagService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

@Stateless
public class HashtagRepo extends Repository<Hashtag, String> {
    @Inject
    private HashtagService hashtagService;

    public HashtagRepo() { super(Hashtag.class); }

    @Override
    public Hashtag save(Hashtag tag) {
        tag = hashtagService.create(tag.getValue());
        return super.save(tag);
    }

    public List<Kweet> getKweetsWithTag(String tag){
        return getById(tag).getKweets();
    }

    public List<Hashtag> getTopRated() {
        return em.createNamedQuery("hashtag.getTop", Hashtag.class).setMaxResults(10).getResultList();
    }
}
