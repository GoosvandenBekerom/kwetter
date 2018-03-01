package com.goosvandenbekerom.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
public class Kweet {
    @Id
    @GeneratedValue
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private User owner;

    private String message;
    private int likeCount;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Hashtag> hashtags;
    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Mention> mentions;

    private Date created;

    @PrePersist
    protected void onCreate() {
        created = new Date();
    }

    public Kweet() {}
    public Kweet(User owner, String message) {
        this.owner = owner;
        this.message = message;
        this.hashtags = new ArrayList<>();
        this.mentions = new ArrayList<>();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(int likeCount) {
        this.likeCount = likeCount;
    }

    public int addLike() {
        return ++this.likeCount;
    }

    public List<Hashtag> getHashtags() {
        if (hashtags == null) {
            hashtags = new ArrayList<>();
        }
        return hashtags;
    }

    public void setHashtags(List<Hashtag> hashtags) {
        this.hashtags = hashtags;
    }

    public List<Mention> getMentions() {
        if (mentions == null) {
            mentions = new ArrayList<>();
        }
        return mentions;
    }

    public void setMentions(List<Mention> mentions) {
        this.mentions = mentions;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Kweet)) return false;
        Kweet kweet = (Kweet) o;
        return getId() == kweet.getId() &&
                getLikeCount() == kweet.getLikeCount() &&
                Objects.equals(getOwner(), kweet.getOwner()) &&
                Objects.equals(getMessage(), kweet.getMessage()) &&
                Objects.equals(getHashtags(), kweet.getHashtags()) &&
                Objects.equals(getMentions(), kweet.getMentions()) &&
                Objects.equals(getCreated(), kweet.getCreated());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getOwner(), getMessage(), getLikeCount(), getHashtags(), getMentions(), getCreated());
    }
}