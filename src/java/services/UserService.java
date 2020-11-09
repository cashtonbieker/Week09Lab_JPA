package services;

import database.UserDB;
import models.User;
import java.util.List;

public class UserService {

    private UserDB userDB;

    public UserService() {
        userDB = new UserDB();
    }

    public User get(String username) throws Exception {
        return userDB.getUser(username);
    }

    public List<User> getAll() throws Exception {
        return userDB.getAll();
    }

    public void update(String username, String password, String firstname, String lastname, String email) throws Exception {
        User user = get(username);
        user.setPassword(password);
        user.setFirstname(firstname);
        user.setLastname(lastname);
        user.setEmail(email);
        userDB.update(user);
    }

    public void delete(String username) throws Exception {
        User deletedUser = userDB.getUser(username);
        // do not allow the admin to be deleted
        if (!deletedUser.getUsername().equals("admin")) {
            userDB.delete(deletedUser);
        }
        
    }

    public void insert(String username, String password, String firstname, String lastname, String email) throws Exception {
        User user = new User(username, password, firstname, lastname, email);
        userDB.insert(user);
    }
}
