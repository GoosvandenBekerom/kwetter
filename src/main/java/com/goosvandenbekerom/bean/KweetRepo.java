package com.goosvandenbekerom.bean;

import com.goosvandenbekerom.annotation.Profanity;
import com.goosvandenbekerom.model.Hashtag;
import com.goosvandenbekerom.model.Kweet;
import com.goosvandenbekerom.model.Mention;
import com.goosvandenbekerom.model.User;
import com.goosvandenbekerom.service.HashtagService;
import com.goosvandenbekerom.util.RegexHelpers;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Stateless
public class KweetRepo extends Repository<Kweet, Long> {
    @Inject
    private HashtagService hashtagService;
    @Inject
    private UserRepo userRepo;

    public KweetRepo() { super(Kweet.class); }

    @Override
    @Profanity
    public Kweet save(Kweet kweet) {
        this.processKweet(kweet);
        return super.save(kweet);
    }

    public void toggleLike(long kweetId, String username) {
        Kweet kweet = getById(kweetId);
        User user = userRepo.getById(username);
        if (kweet.likedBy(user)) kweet.removeLike(user);
        else kweet.addLike(user);
    }

    public User getOwner(long kweetId) {
        return getById(kweetId).getOwner();
    }

    public List<Mention> getMentions(long kweetId) {
        return getById(kweetId).getMentions();
    }

    public List<Hashtag> getHashtags(long kweetId) {
        return getById(kweetId).getHashtags();
    }

    public List<User> getLikesById(long kweetId) {
        return getById(kweetId).getLikes();
    }

    private void processKweet(Kweet kweet) {
        this.processHashtags(kweet);
        this.processMentions(kweet);
    }
    /**
     * Finds all hashtags and adds them to kweet
     * @param kweet to process hashtags from
     */
    private void processHashtags(Kweet kweet) {
        for(String tag : processRegex(kweet.getMessage(), RegexHelpers.HASHTAG)) {
            kweet.getHashtags().add(hashtagService.create(tag));
        }
    }
    /**
     * Finds all mentions and adds them to kweet
     * @param kweet to process mentions from
     */
    private void processMentions(Kweet kweet) {
        for(String username : processRegex(kweet.getMessage(), RegexHelpers.MENTION)) {
            if (!userRepo.exists(username)) continue;

            User user = userRepo.getById(username);
            kweet.getMentions().add(new Mention(user, kweet));
        }
    }
    /**
     * Find all unique occurrences in a string
     * @param value string to search in
     * @param regex pattern to match
     * @return all matches of the given regex pattern
     */
    private Set<String> processRegex(String value, String regex) {
        Set<String> results = new HashSet<>();
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(value);
        while(matcher.find()) {
            results.add(matcher.group());
        }
        return results;
    }
}
