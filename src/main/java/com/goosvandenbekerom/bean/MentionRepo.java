package com.goosvandenbekerom.bean;

import com.goosvandenbekerom.model.Kweet;
import com.goosvandenbekerom.model.Mention;
import com.goosvandenbekerom.model.User;

import javax.ejb.Stateless;

@Stateless
public class MentionRepo extends Repository<Mention, Long> {
    public MentionRepo() { super(Mention.class); }

    public Kweet getKweetFromMention(long mentionId) {
        Mention mention = getById(mentionId);
        if (mention == null) throw notFound(mentionId);
        return mention.getKweet();
    }

    public User getUserFromMention(long mentionId) {
        Mention mention = getById(mentionId);
        if (mention == null) throw notFound(mentionId);
        return mention.getUser();
    }
}
