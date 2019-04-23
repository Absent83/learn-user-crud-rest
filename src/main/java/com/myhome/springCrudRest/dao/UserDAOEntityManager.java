package com.myhome.springCrudRest.dao;

import com.myhome.springCrudRest.model.User;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

//@Repository
@Component
public class UserDAOEntityManager implements UserDAO {

    //@Autowired
    @PersistenceContext
    EntityManager entityManager;

    //language=SQL
    private String SQL_GET_ALL = "SELECT u FROM User u ORDER BY u.id";

    //language=SQL
    private String SQL_GET_BY_FIRSTNAME = "SELECT u FROM User u WHERE u.firstName = :firstName";

    //language=SQL
    private String SQL_GET_BY_USERNAME = "SELECT u FROM User u WHERE u.username = :username";

    @Override
    public Optional<User> get(long id) {
        return Optional.ofNullable(entityManager.find(User.class, id));
    }

    @Override
    public Optional<User> getByUsername(String username) {
        User user = (User) entityManager.createQuery(SQL_GET_BY_USERNAME)
                .setParameter("username", username).getSingleResult();

        return Optional.ofNullable(user);
    }


    @Override
    public List<User> getByFirstName(String firstName) {
        return entityManager.createQuery(SQL_GET_BY_FIRSTNAME)
                .setParameter("firstName", firstName).getResultList();
    }


    @Override
    public List<User> getAll() {
        return entityManager.createQuery(SQL_GET_ALL).getResultList();
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