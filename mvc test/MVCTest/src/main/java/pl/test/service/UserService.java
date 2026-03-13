package pl.test.service;

import pl.test.model.User;

public class UserService {

    public boolean validatePassword(String password) {
        return password != null && password.length() >= 8;
    }

    public boolean validateUser(User user) {
        return validatePassword(user.getPassword());
    }
}