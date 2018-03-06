package com.goosvandenbekerom.bean;

import com.goosvandenbekerom.Exception.UnknownUserException;
import com.goosvandenbekerom.Exception.UserAlreadyFollowedException;
import com.goosvandenbekerom.Exception.UsernameExistsException;
import com.goosvandenbekerom.Exception.WrongUsernameOrPasswordException;
import com.goosvandenbekerom.model.Token;
import com.goosvandenbekerom.model.User;
import org.mindrot.jbcrypt.BCrypt;

import javax.ejb.Stateless;
import javax.ws.rs.BadRequestException;
import java.util.List;

@Stateless
public class UserRepo extends Repository<User, String> {
    public UserRepo() { super(User.class); }

    @Override
    public User save(User entity) {
        User user = em.find(User.class, entity.getUsername());
        if (user != null) {
            throw new UsernameExistsException();
        }

        entity.setPassword(BCrypt.hashpw(entity.getPassword(), BCrypt.gensalt()));
        return super.save(entity);
    }

    public Token login(String username, String password) {
        User user = getById(username);

        if (!BCrypt.checkpw(password, user.getPassword())) {
            throw new WrongUsernameOrPasswordException();
        }

        return user.generateToken();
    }

    public void followUser(String follower, String username) {
        User user = getById(follower);
        User userToFollow = em.find(User.class, username);
        if (user == null || userToFollow == null) {
            throw new UnknownUserException();
        }
        if (user.getFollowing().contains(userToFollow)) {
            throw new UserAlreadyFollowedException();
        }
        if (user.equals(userToFollow)) {
            throw new BadRequestException("You can't follow yourself.");
        }
        user.getFollowing().add(userToFollow);
        userToFollow.getFollowers().add(user);
    }

    public List<User> getFollowing(String username) {
        User user = getById(username);
        if (user == null) {
            throw new UnknownUserException();
        }
        return user.getFollowing();
    }

    public List<User> getFollowers(String username) {
        User user = getById(username);
        if (user == null) {
            throw new UnknownUserException();
        }
        return user.getFollowers();
    }
}
