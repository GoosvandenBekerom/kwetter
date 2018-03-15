package com.goosvandenbekerom.bean;

import com.goosvandenbekerom.model.Group;
import com.goosvandenbekerom.model.Kweet;
import com.goosvandenbekerom.model.User;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

@Startup
@Singleton
public class InitializeDatabaseBean {
    @Inject private UserRepo userRepo;
    @Inject private GroupRepo groupRepo;
    @Inject private KweetRepo kweetRepo;

    @PostConstruct
    void init() {
        // Create users
        User goos = userRepo.save(new User("goos", "test", "Goos van den Bekerom"));
        User hidde = userRepo.save(new User("hidde", "test", "Hidde van den Bekerom"));
        User jan = userRepo.save(new User("jan", "test", "Jan de Testman"));
        User piet = userRepo.save(new User("piet", "test", "Piet Pietermans"));

        // Add user to group
        Group group = groupRepo.save(new Group("regulars"));
        goos.getGroups().add(group);
        group.getUsers().add(goos);

        // Link some followers
        userRepo.followUser(goos.getUsername(), jan.getUsername()); // Goos > Jan
        userRepo.followUser(goos.getUsername(), hidde.getUsername()); // Goos > Hidde
        userRepo.followUser(jan.getUsername(), goos.getUsername()); // Jan > Goos
        userRepo.followUser(piet.getUsername(), goos.getUsername()); // Piet > Goos
        userRepo.followUser(hidde.getUsername(), goos.getUsername()); // Hidde > Goos
        userRepo.followUser(hidde.getUsername(), piet.getUsername()); // Hidde > Piet

        // Send some kweets
        kweetRepo.save(new Kweet(goos, "Mijn eerste #kweet"));
        kweetRepo.save(new Kweet(goos, "Ik zie dat mijn vriend @jan ook kwettert, #toevallig!"));
        kweetRepo.save(new Kweet(hidde, "Wow, ik zag dat mijn broer @goos aan #kwetter deed, dus ik dacht ik wil het ook"));
        kweetRepo.save(new Kweet(jan, "Yeah kwetter, here I am #first #kweet"));
        kweetRepo.save(new Kweet(jan, "@goos haha inderdaad! ik zal jou ook eens volgen."));
        kweetRepo.save(new Kweet(piet, "Ja hoor ook ik heb #kwetter en @geenvrienden"));
    }
}