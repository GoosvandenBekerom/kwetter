package com.goosvandenbekerom.model;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;
import java.util.Objects;

@Entity
public class Mention {
    @Id
    @GeneratedValue
    private long id;

    @ManyToOne
    private User user;

    @ManyToOne
    @JsonbTransient
    private Kweet kweet;

    public Mention() {}
    public Mention(User user, Kweet kweet) {
        this.user = user;
        this.kweet = kweet;
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
