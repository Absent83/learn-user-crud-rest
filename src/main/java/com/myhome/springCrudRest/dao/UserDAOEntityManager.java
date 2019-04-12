package com.myhome.springCrudRest.dao;

import com.myhome.springCrudRest.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;


@Component
public class UserDAOEntityManager implements UserDAO {

    //  @PersistenceContext //todo когда используется @PersistenceContext ??
    @Autowired
    EntityManager entityManager;

    //language=SQL
    private String SQL_GET_ALL = "SELECT u FROM User u";

    //language=SQL
    private String SQL_GET_BY_FIRSTNAME = "SELECT u FROM User u WHERE u.firstName = :firstName";

    //language=SQL
    private String SQL_GET_BY_USERNAME = "SELECT u FROM User u WHERE u.username = :username";

    @Override
    public Optional<User> get(long id) {
        //EntityManager entityManager = entityManagerFactory.createEntityManager();

        //entityManager.getTransaction().begin(); //todo посмотреть, нужны ли транзакции
        User user = entityManager.find(User.class, id);
        //entityManager.detach(user);
        //entityManager.getTransaction().commit();


        if (user == null) {
            return Optional.empty();
        } else {
            return Optional.of(user);
        }
    }

    @Override
    public Optional<User> getByUsername(String username) {
        //EntityManager entityManager = entityManagerFactory.createEntityManager();

        User user = (User) entityManager.createQuery(SQL_GET_BY_USERNAME)
                .setParameter("username", username).getSingleResult(); //todo почему подчеркивает


        if (user == null){
            return Optional.empty();
        }
        else return Optional.of(user);
    }


    @Override
    public Optional<List<User>> getByFirstName(String firstName) {
        //EntityManager entityManager = entityManagerFactory.createEntityManager();

        List<User> users = entityManager.createQuery(SQL_GET_BY_FIRSTNAME)
                .setParameter("firstName", firstName).getResultList(); //todo почему подчеркивает "name"

        if (users == null){
            return Optional.empty();
        }
        else return Optional.of(users);
    }


    @Override
    public Optional<List<User>> getAll() {

        List<User> users = entityManager.createQuery(SQL_GET_ALL).getResultList();

        if (users == null){
            return Optional.empty();
        }
        else return Optional.of(users);
    }


    @Override
    public void add(User user) {
        entityManager.merge(user);
    }


    @Override
    public void update(User user) {
        entityManager.merge(user);
    }


    @Override
    public void delete(long id) {
        entityManager.remove(get(id).orElseThrow(IllegalArgumentException::new));
    }
}