package com.myhome.springCrudRest.dao;

import com.myhome.springCrudRest.model.Role;
import com.myhome.springCrudRest.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Supplier;

/**
 * @author Nick Dolgopolov (nick_kerch@mail.ru; https://github.com/Absent83/)
 */

//@Repository
@Component
public class RoleDAOEntityManager implements RoleDAO {

    //language=SQL
    private static final String SQL_GET_ALL = "SELECT r FROM Role r";


    //@Autowired
    @PersistenceContext
    EntityManager entityManager;


    @Override
    public Optional<Role> get(long id) {
        return Optional.ofNullable(entityManager.find(Role.class, id));
    }


    @Override
    public List<Role> getAll() {
        return entityManager.createQuery(SQL_GET_ALL).getResultList();

    }


    @Override
    public void add(Role role) {
        entityManager.merge(role);
    }


    @Override
    public void update(Role role) {
        entityManager.merge(role);
    }


    @Override
    public void delete(long id) {
        entityManager.remove(get(id).orElseThrow(IllegalArgumentException::new));
    }
}