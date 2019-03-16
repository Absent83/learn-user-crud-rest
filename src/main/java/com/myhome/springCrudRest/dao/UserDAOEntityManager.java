package com.myhome.springCrudRest.dao;

import com.myhome.springCrudRest.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import java.util.Optional;


@Component
public class UserDAOEntityManager implements UserDAO {

    //@PersistenceContext
    @Autowired
    EntityManagerFactory entityManagerFactory;

    //language=SQL
    private String SQL_GET_BY_NAME = "SELECT * from users WHERE id = ?1";

    @Override
    public Optional<User> get(long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        User user = entityManager.find(User.class, id);
        entityManager.detach(user);

        if (user == null){
            return Optional.empty();
        }
        else return Optional.of(user);
    }


    @Override
    public Optional<User> getByName(String name) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        User user = (User) entityManager.createQuery(SQL_GET_BY_NAME)
                .setParameter(1, name).getSingleResult();


        if (user == null){
            return Optional.empty();
        }
        else return Optional.of(user);
    }


    @Override
    public void add(User dataSet) {

    }


    @Override
    public void update(User dataSet) {

    }


    @Override
    public void delete(long id) {

    }
}
