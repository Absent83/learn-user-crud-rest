package com.myhome.springCrudRest.dao;

import com.myhome.springCrudRest.model.Role;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

/**
 * @author Nick Dolgopolov (nick_kerch@mail.ru; https://github.com/Absent83/)
 */

//@Repository
@Component
public class RoleDAOEntityManager implements RoleDAO {

    //language=SQL
    private static final String SQL_GET_ALL = "SELECT r FROM Role r";


    //language=SQL
    private String SQL_GET_BY_AUTHORITY = "SELECT r FROM Role r WHERE r.authority = :authority";


    //@Autowired
    @PersistenceContext
    EntityManager entityManager;


    @Override
    public Optional<Role> get(long id) {
        return Optional.ofNullable(entityManager.find(Role.class, id));
    }


    @Override
    public Optional<Role> getByAuthority(String authority) {
        Role role = (Role) entityManager.createQuery(SQL_GET_BY_AUTHORITY)
                .setParameter("authority", authority).getSingleResult();

        return Optional.ofNullable(role);
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