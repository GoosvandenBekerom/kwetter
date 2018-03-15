package com.goosvandenbekerom.bean;

import com.goosvandenbekerom.model.Group;

import javax.ejb.Stateless;

@Stateless
public class GroupRepo extends Repository<Group, String> {
    public GroupRepo() { super(Group.class); }
}
