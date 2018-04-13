package com.goosvandenbekerom.util;

import com.goosvandenbekerom.Resource.HashtagResource;
import com.goosvandenbekerom.Resource.KweetResource;
import com.goosvandenbekerom.Resource.MentionResource;
import com.goosvandenbekerom.Resource.UserResource;
import com.goosvandenbekerom.model.Hashtag;
import com.goosvandenbekerom.model.Kweet;
import com.goosvandenbekerom.model.Mention;
import com.goosvandenbekerom.model.User;

import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
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
        URI baseUri = uri.getBaseUriBuilder().path(UserResource.class).path(user.getUsername()).build();
        user.addLink("self", baseUri);
        user.addLink("following", UriBuilder.fromUri(baseUri).path("following").build());
        user.addLink("followers", UriBuilder.fromUri(baseUri).path("followers").build());
        return user;
    }

    static public Kweet kweet(Kweet kweet, UriInfo uri) {
        URI baseUri = uri.getBaseUriBuilder().path(KweetResource.class).path(String.valueOf(kweet.getId())).build();
        kweet.addLink("self", baseUri);
        kweet.addLink("owner", UriBuilder.fromUri(baseUri).path("owner").build());
        kweet.addLink("mentions", UriBuilder.fromUri(baseUri).path("mentions").build());
        kweet.addLink("hashtags", UriBuilder.fromUri(baseUri).path("hashtags").build());
        return kweet;
    }

    static public Mention mention(Mention mention, UriInfo uri) {
        URI baseUri = uri.getBaseUriBuilder().path(MentionResource.class).path(String.valueOf(mention.getId())).build();
        mention.addLink("self", baseUri);
        mention.addLink("kweet", UriBuilder.fromUri(baseUri).path("kweet").build());
        return mention;
    }

    static public Hashtag hashtag(Hashtag hashtag, UriInfo uri) {
        URI baseUri = uri.getBaseUriBuilder().path(HashtagResource.class).path(hashtag.getValue()).build();
        hashtag.addLink("self", baseUri);
        hashtag.addLink("kweets", UriBuilder.fromUri(baseUri).path("kweets").build());
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
