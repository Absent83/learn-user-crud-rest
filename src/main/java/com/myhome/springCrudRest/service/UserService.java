package com.myhome.springCrudRest.service;

import com.myhome.springCrudRest.model.User;

import java.util.Optional;

public interface UserService {
    Optional<User> get(long id);
    Optional<User> getByName(String name);

    void add(User dataSet);
    void update(User dataSet);
    void delete(long id);
}

