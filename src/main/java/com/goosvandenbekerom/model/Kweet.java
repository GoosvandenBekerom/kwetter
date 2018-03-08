package com.goosvandenbekerom.model;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
public class Kweet extends HateoasModel {
    @Id
    @GeneratedValue
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private User owner;

    private String message;
    private int likeCount;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Hashtag> hashtags;
    @OneToMany(mappedBy = "kweet", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Mention> mentions;

    @OneToMany(fetch = FetchType.LAZY)
    @JsonbTransient
    private List<User> likes;

    private Date created;
    private Date updated;

    @PrePersist
    protected void onCreate() {
        created = updated = new Date();
    }
    @PreUpdate
    protected void onUpdate() {
        updated = new Date();
    }

    public Kweet() {}
    public Kweet(User owner, String message) {
        this.owner = owner;
        this.message = message;
        this.hashtags = new ArrayList<>();
        this.mentions = new ArrayList<>();
        this.likes = new ArrayList<>();
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

    public int addLike(User user) {
        if (likedBy(user)) {
            return likeCount;
        }
        likes.add(user);
        return ++likeCount;
    }

    public int removeLike(User user) {
        if (!likedBy(user)) {
            return likeCount;
        }
        likes.remove(user);
        return --likeCount;
    }

    public boolean likedBy(User user) {
        return getLikes().contains(user);
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

    public List<User> getLikes() {
        if (likes == null) {
            likes = new ArrayList<>();
        }
        return likes;
    }

    public void setLikes(List<User> likes) {
        this.likes = likes;
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