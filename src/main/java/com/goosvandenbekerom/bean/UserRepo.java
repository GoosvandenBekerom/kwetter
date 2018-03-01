package com.goosvandenbekerom.bean;

import com.goosvandenbekerom.Exception.UserAlreadyFollowedException;
import com.goosvandenbekerom.Exception.UsernameExistsException;
import com.goosvandenbekerom.Exception.WrongUsernameOrPasswordException;
import com.goosvandenbekerom.model.Token;
import com.goosvandenbekerom.model.User;
import org.mindrot.jbcrypt.BCrypt;

import javax.ejb.Stateless;

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

    public boolean followUser(String follower, String username) {
        User user = em.find(User.class, follower);
        User userToFollow = em.find(User.class, username);
        if (user == null || userToFollow == null) {
            return false;
        }
        if (user.getFollowing().contains(userToFollow)) {
            throw new UserAlreadyFollowedException();
        }
        return user.getFollowing().add(userToFollow);
    }
}
