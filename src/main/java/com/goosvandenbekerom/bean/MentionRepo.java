package com.goosvandenbekerom.bean;

import com.goosvandenbekerom.model.Kweet;
import com.goosvandenbekerom.model.Mention;

import javax.ejb.Stateless;
import javax.ws.rs.NotFoundException;

@Stateless
public class MentionRepo extends Repository<Mention, Long> {
    public MentionRepo() { super(Mention.class); }

    public Kweet getKweetFromMention(long mentionId) {
        Mention mention = em.find(Mention.class, mentionId);
        if (mention == null) {
            throw new NotFoundException("Mention with id " + mentionId + " not found");
        }
        return mention.getKweet();
    }
}
