package com.myhome.springCrudRest.dao;

import com.myhome.springCrudRest.model.Role;

import java.util.Optional;
import java.util.Set;

/**
 * @author Nick Dolgopolov (nick_kerch@mail.ru; https://github.com/Absent83/)
 */
public interface RoleDAO {
    Optional<Role> get(long id);

    Optional<Set<Role>> getAll();

    void add(Role role);

    void update(Role role);

    void delete(long id);
}