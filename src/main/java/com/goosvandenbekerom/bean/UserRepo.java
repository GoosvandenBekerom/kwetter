package com.goosvandenbekerom.bean;

import com.goosvandenbekerom.Exception.UserAlreadyFollowedException;
import com.goosvandenbekerom.Exception.UsernameExistsException;
import com.goosvandenbekerom.Exception.WrongUsernameOrPasswordException;
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
        if (exists(entity.getUsername()))
            throw new UsernameExistsException();

        entity.setPassword(BCrypt.hashpw(entity.getPassword(), BCrypt.gensalt()));
        return super.save(entity);
    }

    public String login(String username, String password) {
        User user = getById(username);

        if (!BCrypt.checkpw(password, user.getPassword()))
            throw new WrongUsernameOrPasswordException();

        return user.generateToken();
    }

    public void followUser(String follower, String username) {
        User user = getById(follower);
        User userToFollow = getById(username);

        if (user.getFollowing().contains(userToFollow))
            throw new UserAlreadyFollowedException();
        if (user.equals(userToFollow))
            throw new BadRequestException("You can't follow yourself.");

        user.getFollowing().add(userToFollow);
        userToFollow.getFollowers().add(user);
    }

    public List<User> getFollowing(String username) {
        return getById(username).getFollowing();
    }

    public List<User> getFollowers(String username) {
        return getById(username).getFollowers();
    }
}
