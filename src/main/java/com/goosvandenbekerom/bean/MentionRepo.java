package com.goosvandenbekerom.bean;

import com.goosvandenbekerom.Exception.UnauthorizedException;
import com.goosvandenbekerom.model.Kweet;
import com.goosvandenbekerom.model.Mention;
import com.goosvandenbekerom.model.User;

import javax.ejb.Stateless;
import javax.persistence.Query;
import java.util.List;

@Stateless
public class MentionRepo extends Repository<Mention, Long> {
    public MentionRepo() { super(Mention.class); }

    public Kweet getKweetFromMention(long id) {
        return getById(id).getKweet();
    }

    public User getUserFromMention(long id) {
        return getById(id).getUser();
    }

    @SuppressWarnings("unchecked") // stackoverflow question 115692
    public List<Mention> getUnseen(String username) {
        Query q = em.createNamedQuery("mention.getUnseen", Mention.class);
        q.setParameter("username", username);
        return q.getResultList();
    }

    public void setSeen(long id, String username) {
        Mention mention = getById(id);

        if (mention.isSeen()) return;
        if (!mention.getUser().getUsername().equals(username)) {
            throw new UnauthorizedException();
        }

        mention.setSeen(true);
    }
}
