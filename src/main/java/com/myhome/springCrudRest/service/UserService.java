package com.myhome.springCrudRest.service;

import com.myhome.springCrudRest.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    Optional<User> get(long id);
    Optional<User> getByUserName(String userName);

    Optional<List<User>> getAll();
    Optional<List<User>> getByFirstName(String firstName);

    void add(User user);
    void update(User user);
    void delete(long id);
}

