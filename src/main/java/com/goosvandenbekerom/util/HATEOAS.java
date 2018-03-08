package com.goosvandenbekerom.util;

import com.goosvandenbekerom.Resource.MentionResource;
import com.goosvandenbekerom.Resource.UserResource;
import com.goosvandenbekerom.model.Hashtag;
import com.goosvandenbekerom.model.Kweet;
import com.goosvandenbekerom.model.Mention;
import com.goosvandenbekerom.model.User;

import javax.ws.rs.core.UriInfo;
import java.util.List;

/**
 * Helper class to create HATEOAS specifications for domain models
 * - User
 * - Kweet
 * - Mention
 * - Hashtag
 */
public class HATEOAS {
    static public User user(User user, UriInfo uri) {
        user.addLink(
                "self",
                uri.getBaseUriBuilder().path(UserResource.class).path(user.getUsername()).build()
        );
        user.addLink(
                "following",
                uri.getBaseUriBuilder().path(UserResource.class).path(user.getUsername()).path("following").build()
        );
        user.addLink(
                "followers",
                uri.getBaseUriBuilder().path(UserResource.class).path(user.getUsername()).path("followers").build()
        );
        return user;
    }

    static public Kweet kweet(Kweet kweet, UriInfo uri) {
        kweet.addLink(
                "self",
                uri.getBaseUriBuilder().path(UserResource.class).path(String.valueOf(kweet.getId())).build()
        );
        //todo add links
        return kweet;
    }

    static public Mention mention(Mention mention, UriInfo uri) {
        mention.addLink(
                "self",
                uri.getBaseUriBuilder().path(MentionResource.class).path(String.valueOf(mention.getId())).build()
        );
        mention.addLink(
                "user",
                uri.getBaseUriBuilder().path(MentionResource.class).path(String.valueOf(mention.getId())).path("user").build()
        );
        mention.addLink(
                "kweet",
                uri.getBaseUriBuilder().path(MentionResource.class).path(String.valueOf(mention.getId())).path("kweet").build()
        );
        return mention;
    }

    static public Hashtag hashtag(Hashtag hashtag, UriInfo uri) {
        //todo add links
        return hashtag;
    }

    static public List<User> userList(List<User> users, UriInfo uri) {
        users.forEach(user -> user(user, uri));
        return users;
    }

    static public List<Kweet> kweetList(List<Kweet> kweets, UriInfo uri) {
        kweets.forEach(kweet -> kweet(kweet, uri));
        return kweets;
    }

    static public List<Mention> mentionList(List<Mention> mentions, UriInfo uri) {
        mentions.forEach(mention -> mention(mention, uri));
        return mentions;
    }

    static public List<Hashtag> hashtagList(List<Hashtag> hashtags, UriInfo uri) {
        hashtags.forEach(hashtag -> hashtag(hashtag, uri));
        return hashtags;
    }
}
