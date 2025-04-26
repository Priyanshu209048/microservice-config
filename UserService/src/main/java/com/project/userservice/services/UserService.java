package com.project.userservice.services;

import com.project.userservice.entities.User;

import java.util.List;

public interface UserService {

    User createUser(User user);
    User updateUser(User user, String id);
    User getUser(String id);
    List<User> getUsers();
    void deleteUser(String id);

}
