package com.myhome.springCrudRest.service;

import com.myhome.springCrudRest.dao.UserDAO;
import com.myhome.springCrudRest.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
//@Component
public class UserServiceImpl implements UserService {

    private final UserDAO userDAO;

    @Autowired
    public UserServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<User> get(long id) {
        return userDAO.get(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<User> getByUsername(String userName) {
        return userDAO.getByUsername(userName);
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> getByFirstName(String firstName) {
        return userDAO.getByFirstName(firstName);
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> getAll() {
        return userDAO.getAll();
    }

    @Override
    @Transactional
    public void add(User user) {
        userDAO.add(user);
    }

    @Override
    @Transactional
    public void update(User user) {
        userDAO.update(user);
    }

    @Override
    @Transactional
    public void delete(long id) {
        userDAO.delete(id);
    }
}
