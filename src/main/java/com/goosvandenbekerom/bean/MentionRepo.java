package com.goosvandenbekerom.bean;

import com.goosvandenbekerom.model.Kweet;
import com.goosvandenbekerom.model.Mention;
import com.goosvandenbekerom.model.User;

import javax.ejb.Stateless;

@Stateless
public class MentionRepo extends Repository<Mention, Long> {
    public MentionRepo() { super(Mention.class); }

    public Kweet getKweetFromMention(long id) {
        return getById(id).getKweet();
    }

    public User getUserFromMention(long id) {
        return getById(id).getUser();
    }
}
