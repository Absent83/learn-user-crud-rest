package com.myhome.springCrudRest.dao;

import com.myhome.springCrudRest.model.Role;
import com.myhome.springCrudRest.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Supplier;

/**
 * @author Nick Dolgopolov (nick_kerch@mail.ru; https://github.com/Absent83/)
 */

@Component
public class RoleDAOEntityManager implements RoleDAO {

    //language=SQL
    private static final String SQL_GET_ALL = "SELECT r FROM Role r";


    @Autowired
    EntityManager entityManager;


    @Override
    public Optional<Role> get(long id) {
        Role role = entityManager.find(Role.class, id);

        if (role == null) {
            return Optional.empty();
        } else {
            return Optional.of(role);
        }
    }


    @Override
    public Optional<Set<Role>> getAll() {

        List resultList = entityManager.createQuery(SQL_GET_ALL).getResultList();

        if (resultList == null){
            return Optional.empty();
        }
        else {
            Set<Role> roles = new HashSet<>(resultList);
            return Optional.of(roles);
        }
    }


    @Override
    public void add(Role role) {
        entityManager.merge(role);
    }


    @Override
    public void update(Role role) {
        //entityManager.getTransaction().begin();
        entityManager.merge(role);
        //entityManager.getTransaction().commit();
    }


    @Override
    public void delete(long id) {
        entityManager.remove(get(id).orElseThrow(IllegalArgumentException::new));
    }
}