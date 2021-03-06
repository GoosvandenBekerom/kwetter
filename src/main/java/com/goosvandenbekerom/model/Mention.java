package com.goosvandenbekerom.model;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;
import java.util.Objects;

@Entity
@NamedQueries(
        @NamedQuery(
                name = "mention.getUnseen",
                query = "SELECT m FROM Mention m WHERE m.user.username = :username AND m.seen = false"
        )
)
public class Mention extends HateoasModel {
    @Id
    @GeneratedValue
    private long id;

    @ManyToOne
    private User user;

    @ManyToOne
    @JsonbTransient
    private Kweet kweet;

    private boolean seen;

    public Mention() {}
    public Mention(User user, Kweet kweet) {
        this.user = user;
        this.kweet = kweet;
        this.seen = false;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User mentionedUser) {
        this.user = mentionedUser;
    }

    public Kweet getKweet() {
        return kweet;
    }

    public void setKweet(Kweet kweet) {
        this.kweet = kweet;
    }

    public boolean isSeen() {
        return seen;
    }

    public void setSeen(boolean seen) {
        this.seen = seen;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Mention)) return false;
        Mention mention = (Mention) o;
        return getId() == mention.getId() &&
                Objects.equals(getUser(), mention.getUser());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getUser());
    }
}
