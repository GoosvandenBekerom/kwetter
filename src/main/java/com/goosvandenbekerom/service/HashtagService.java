package com.goosvandenbekerom.service;

import com.goosvandenbekerom.model.Hashtag;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class HashtagService {
    @PersistenceContext(unitName = "kwetter")
    private EntityManager em;

    public Hashtag createHashtag(String tag) {
        Hashtag hashtag = em.find(Hashtag.class, tag);
        hashtag = hashtag != null ? hashtag : new Hashtag(tag);
        hashtag.increaseCount();
        return hashtag;
    }
}
