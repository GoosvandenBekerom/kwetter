package com.goosvandenbekerom.bean;

import com.goosvandenbekerom.model.Kweet;

import javax.ejb.Stateless;

@Stateless
public class KweetRepo extends Repository<Kweet> {
    public KweetRepo() { super(Kweet.class); }
}
