package ru.bl00dphant0m.userservice.service;

import ru.bl00dphant0m.userservice.model.entity.User;

public interface UserService {
    User getUserById(long id);
    User saveUser(User user);
    void deleteUser(long id);
}
