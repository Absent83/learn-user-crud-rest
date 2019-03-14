package com.myhome.springCrudRest.model;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class UserDAOHibernate implements UserDAO {

    @Autowired
    private SessionFactory sessionFactory;


    public UserDAOHibernate(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    public UserDataSet get(long id) {
        Session session = sessionFactory.openSession(); //todo тут не надо try-catch?
        UserDataSet userDataSet = session.load(UserDataSet.class, id);
        session.close();
        return userDataSet;
    }


    public UserDataSet getByName(String name) {
        return null;
    }


    public void add(UserDataSet userDataSet) {
        Session session = sessionFactory.openSession();

        Transaction tx = session.beginTransaction();
        //todo using Hibernate session transaction management.
        // But we can also use Spring declarative transaction management
        // using @Transactional annotation


        session.save(userDataSet);
        tx.commit();
        session.close();
    }


    public void delete(long id) {
    }
}
