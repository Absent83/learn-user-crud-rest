package com.myhome.springCrudRest.dao;

import com.myhome.springCrudRest.model.User;

import java.util.List;
import java.util.Optional;

public interface UserDAO { //CRUD Create Read Update Delete
    Optional<User> get(long id);
    Optional<User> getByLogin(String login);

    Optional<List<User>> getByName(String name);
    Optional<List<User>> getAll();

    void add(User user);
    void update(User user);
    void delete(long id);

}
