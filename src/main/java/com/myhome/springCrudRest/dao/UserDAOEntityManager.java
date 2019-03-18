package com.myhome.springCrudRest.dao;

import com.myhome.springCrudRest.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
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
    private String SQL_GET_BY_NAME = "SELECT u FROM User u WHERE u.name = :name";

    @Override
    public Optional<User> get(long id) {
        //EntityManager entityManager = entityManagerFactory.createEntityManager();
        //todo чем отличаеся работа с EntityManagerFactory. Всегда делаем create EntitiyManaget и Close()
        //todo использовать try catch с ресурсами?
        //todo использовать ли Optional ?

        entityManager.getTransaction().begin();
        User user = entityManager.find(User.class, id);
        //entityManager.detach(user);
        entityManager.getTransaction().commit();


        if (user == null) { //todo написать одной строчкой
            return Optional.empty();
        } else {
            return Optional.of(user);
        }
    }


    @Override
    public Optional<List<User>> getByName(String name) {
        //EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();
        List<User> users = entityManager.createQuery(SQL_GET_BY_NAME)
                .setParameter("name", name).getResultList(); //todo почему подчеркивает
        entityManager.getTransaction().commit();


        if (users == null){
            return Optional.empty();
        }
        else return Optional.of(users);
    }


    @Override
    public Optional<List<User>> getAll() {

        entityManager.getTransaction().begin();
        List<User> users = entityManager.createQuery(SQL_GET_ALL).getResultList();
        entityManager.getTransaction().commit();

        if (users == null){
            return Optional.empty();
        }
        else return Optional.of(users);
    }


    @Override
    public void add(User user) {
        entityManager.getTransaction().begin();
        entityManager.merge(user);
        entityManager.getTransaction().commit();
    }


    @Override
    public void update(User user) {
        entityManager.getTransaction().begin();
        entityManager.merge(user);
        entityManager.getTransaction().commit();
    }


    @Override
    public void delete(long id) {

        Optional<User> userCandidate = get(id);

        if (userCandidate.isPresent()){
            entityManager.getTransaction().begin();
            entityManager.remove(userCandidate.get());
            entityManager.getTransaction().commit();
        }
        else {
            throw new IllegalArgumentException();
        }
    }
}