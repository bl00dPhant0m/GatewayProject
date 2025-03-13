package ru.bl00dphant0m.securityservice.service;

import ru.bl00dphant0m.securityservice.model.User;

public interface UserService {
    User findUserById(long id);
    User saveUser(User user);
    User findUserByUsername(String username);
}
