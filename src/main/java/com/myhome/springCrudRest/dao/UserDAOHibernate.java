package com.myhome.springCrudRest.dao;

import com.myhome.springCrudRest.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class UserDAOHibernate implements UserDAO {

    private SessionFactory sessionFactory;

    public UserDAOHibernate(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    public Optional<User> get(long id) {
        Session session = sessionFactory.openSession();
        User user = session.get(User.class, id);
        session.close();

        if (user == null){
            return Optional.empty();
        }
        else return Optional.of(user);
    }

    @Override
    public Optional<User> getByUsername(String username) {
        return Optional.empty();
    }


    public List<User> getByFirstName(String firstName) {
        return new ArrayList<>();
    }

    @Override
    public List<User> getAll() {
        return new ArrayList<>();
    }


    public void add(User user) {
        Session session = sessionFactory.openSession();

        Transaction tx = session.beginTransaction();

        session.save(user);
        tx.commit();
        session.close();
    }

    @Override
    public void update(User dataSet) {

    }


    public void delete(long id) {
    }
}