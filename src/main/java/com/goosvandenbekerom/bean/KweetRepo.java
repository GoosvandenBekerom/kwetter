package com.goosvandenbekerom.bean;

import com.goosvandenbekerom.model.Kweet;
import com.goosvandenbekerom.service.HashtagService;
import com.goosvandenbekerom.util.RegexHelpers;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Stateless
public class KweetRepo extends Repository<Kweet> {
    @Inject
    private HashtagService hashtagService;

    public KweetRepo() { super(Kweet.class); }

    @Override
    public Kweet save(Kweet kweet) {
        this.processKweet(kweet);
        return super.save(kweet);
    }

    private void processKweet(Kweet kweet) {
        this.processHashtags(kweet);
        // todo: process mentions and urls?
    }

    /**
     * Finds all hashtags and adds them to kweet
     * @param kweet to process hashtags from
     */
    private void processHashtags(Kweet kweet) {
        Pattern pattern = Pattern.compile(RegexHelpers.HASHTAG);
        Matcher matcher = pattern.matcher(kweet.getMessage());
        while(matcher.find()) {
            kweet.addHashtag(hashtagService.create(matcher.group()));
        }
    }
}
