package com.goosvandenbekerom.bean;

import com.goosvandenbekerom.Exception.WrongUsernameOrPasswordException;
import com.goosvandenbekerom.model.Credentials;
import com.goosvandenbekerom.model.Token;
import com.goosvandenbekerom.model.User;
import org.mindrot.jbcrypt.BCrypt;

import javax.ejb.Stateless;

@Stateless
public class UserRepo extends Repository<User> {
    public UserRepo() { super(User.class); }

    @Override
    public User save(User entity) {
        entity.setPassword(BCrypt.hashpw(entity.getPassword(), BCrypt.gensalt()));
        return super.save(entity);
    }

    public Token login(Credentials credentials) throws WrongUsernameOrPasswordException {
        User user = getById(credentials.getUsername());

        if (!BCrypt.checkpw(credentials.getPassword(), user.getPassword())) {
            throw new WrongUsernameOrPasswordException();
        }

        return user.generateToken();
    }
}
