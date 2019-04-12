package com.myhome.springCrudRest.service;

import com.myhome.springCrudRest.dao.UserDAO;
import com.myhome.springCrudRest.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDAO;

    @Override
    public Optional<User> get(long id) {
        return userDAO.get(id);
    }

    @Override
    public Optional<User> getByUserName(String userName) {
        return userDAO.getByUsername(userName);
    }

    @Override
    public Optional<List<User>> getByFirstName(String firstName) {
        return userDAO.getByFirstName(firstName);
    }

    @Override
    public Optional<List<User>> getAll() {
        return userDAO.getAll();
    }

    @Override
    public void add(User user) {
        userDAO.add(user);
    }

    @Override
    public void update(User user) {
        userDAO.update(user);
    }

    @Override
    public void delete(long id) {
        userDAO.delete(id);
    }
}
