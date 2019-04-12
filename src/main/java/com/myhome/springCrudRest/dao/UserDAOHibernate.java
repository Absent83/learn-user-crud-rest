package com.myhome.springCrudRest.dao;

import com.myhome.springCrudRest.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;


public class UserDAOHibernate implements UserDAO {

    private SessionFactory sessionFactory;

    @Autowired
    public UserDAOHibernate(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    public Optional<User> get(long id) {
        Session session = sessionFactory.openSession(); //todo тут не надо try-catch?
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


    public Optional<List<User>> getByFirstName(String firstName) {
        return Optional.empty();
    }

    @Override
    public Optional<List<User>> getAll() {
        return Optional.empty();
    }


    public void add(User user) {
        Session session = sessionFactory.openSession();

        Transaction tx = session.beginTransaction();
        //todo using Hibernate session transaction management.
        // But we can also use Spring declarative transaction management
        // using @Transactional annotation


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