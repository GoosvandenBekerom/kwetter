package com.goosvandenbekerom.bean;

import com.goosvandenbekerom.model.Mention;

import javax.ejb.Stateless;

@Stateless
public class MentionRepo extends Repository<Mention> {
    public MentionRepo() { super(Mention.class); }
}
