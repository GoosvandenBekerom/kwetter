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
        userRepo.save(new User("goos", "test", "Goos van den Bekerom"));
        userRepo.save(new User("jan", "test", "Jan de Testman"));
    }
}