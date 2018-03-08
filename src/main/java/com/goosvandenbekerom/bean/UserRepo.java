package com.goosvandenbekerom.bean;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.goosvandenbekerom.Exception.UserAlreadyFollowedException;
import com.goosvandenbekerom.Exception.UsernameExistsException;
import com.goosvandenbekerom.Exception.WrongUsernameOrPasswordException;
import com.goosvandenbekerom.config.JwtConfig;
import com.goosvandenbekerom.model.User;
import org.mindrot.jbcrypt.BCrypt;

import javax.ejb.Stateless;
import javax.ws.rs.BadRequestException;
import java.io.UnsupportedEncodingException;
import java.util.List;

@Stateless
public class UserRepo extends Repository<User, String> {
    public UserRepo() { super(User.class); }

    @Override
    public User save(User user) {
        if (exists(user.getUsername()))
            throw new UsernameExistsException();

        user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));
        return super.save(user);
    }

    public String login(String username, String password) {
        User user = getById(username);
        checkPassword(user, password);
        return generateToken(user.getUsername());
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

    public void changePassword(String username, String password, String newPassword) {
        User user = getById(username);
        checkPassword(user, password);
        user.setPassword(BCrypt.hashpw(newPassword, BCrypt.gensalt()));
    }

    public void changeFullName(String username, String newFullName) {
        User user = getById(username);
        user.setFullName(newFullName);
    }

    private String generateToken(String username) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(JwtConfig.SECRET);
            return JWT.create()
                    .withClaim("user", username)
                    .withIssuer("auth0")
                    .sign(algorithm);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void checkPassword(User user, String password) {
        if (!BCrypt.checkpw(password, user.getPassword()))
            throw new WrongUsernameOrPasswordException();
    }
}
