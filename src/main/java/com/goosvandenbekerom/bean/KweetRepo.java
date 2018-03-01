package com.goosvandenbekerom.bean;

import com.goosvandenbekerom.model.Kweet;
import com.goosvandenbekerom.model.Mention;
import com.goosvandenbekerom.model.User;
import com.goosvandenbekerom.service.HashtagService;
import com.goosvandenbekerom.util.RegexHelpers;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.HashSet;
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
    public Kweet save(Kweet kweet) {
        this.processKweet(kweet);
        return super.save(kweet);
    }

    private void processKweet(Kweet kweet) {
        this.processHashtags(kweet);
        this.processMentions(kweet);
        // todo: process urls?
    }

    /**
     * Finds all hashtags and adds them to kweet
     * @param kweet to process hashtags from
     */
    private void processHashtags(Kweet kweet) {
        for(String tag : processRegex(kweet.getMessage(), RegexHelpers.HASHTAG)) {
            kweet.addHashtag(hashtagService.create(tag));
        }
    }
    /**
     * Finds all mentions and adds them to kweet
     * @param kweet to process mentions from
     */
    private void processMentions(Kweet kweet) {
        for(String mention : processRegex(kweet.getMessage(), RegexHelpers.MENTION)) {
            User user = userRepo.getById(mention);
            if (user == null) continue;
            kweet.addMention(new Mention(user));
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
