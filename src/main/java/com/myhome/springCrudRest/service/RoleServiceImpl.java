package com.myhome.springCrudRest.service;

import com.myhome.springCrudRest.dao.RoleDAO;
import com.myhome.springCrudRest.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.Set;

/**
 * @author Nick Dolgopolov (nick_kerch@mail.ru; https://github.com/Absent83/)
 */

@Component
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDAO roleDAO;

    @Override
    public Optional<Role> get(long id) {
        return roleDAO.get(id);
    }

    @Override
    public Optional<Set<Role>> getAll() {
        return roleDAO.getAll();
    }


    @Override
    public void add(Role role) {
        roleDAO.add(role);
    }

    @Override
    public void update(Role role) {
        roleDAO.update(role);
    }

    @Override
    public void delete(long id) {
        roleDAO.delete(id);
    }
}
