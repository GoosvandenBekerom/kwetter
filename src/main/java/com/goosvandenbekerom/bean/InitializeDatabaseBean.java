package com.goosvandenbekerom.bean;

import com.goosvandenbekerom.model.User;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

@Startup
@Singleton
public class InitializeDatabaseBean {
    @Inject
    private UserRepo userRepo;

    @PostConstruct
    void init() {
        User goos = new User("goos", "test", "Goos van den Bekerom");
        User jan = new User("jan", "test", "Jan de Testman");
        userRepo.save(goos);
        userRepo.save(jan);
        userRepo.followUser(goos.getUsername(), jan.getUsername());
    }
}