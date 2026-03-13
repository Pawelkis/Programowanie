package pl.test.controller;

import pl.test.model.User;
import pl.test.repository.UserRepository;
import pl.test.service.UserService;

public class UserController {

    private UserRepository repository;
    private UserService service;

    public UserController(UserRepository repository, UserService service) {
        this.repository = repository;
        this.service = service;
    }

    public boolean saveUser(User user) {
        if (!service.validateUser(user)) {
            return false;
        }

        try {
            repository.save(user);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}