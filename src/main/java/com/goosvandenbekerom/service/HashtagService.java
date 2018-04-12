package com.goosvandenbekerom.service;

import com.goosvandenbekerom.model.Hashtag;
import com.goosvandenbekerom.model.Kweet;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class HashtagService {
    @PersistenceContext(unitName = "kwetter")
    private EntityManager em;

    public Hashtag create(String tag) {
        Hashtag hashtag = em.find(Hashtag.class, tag);
        hashtag = hashtag != null ? hashtag : new Hashtag(tag);
        hashtag.increaseCount();
        return hashtag;
    }

    public void removeHashtagsOfKweet(Kweet kweet) {
        if (kweet.getHashtags().isEmpty()) return;

        for (Hashtag tag : kweet.getHashtags()) {
            tag.decreaseCount();
        }
    }
}
