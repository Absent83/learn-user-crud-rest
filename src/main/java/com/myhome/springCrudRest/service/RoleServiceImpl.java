package com.myhome.springCrudRest.service;

import com.myhome.springCrudRest.dao.RoleDAO;
import com.myhome.springCrudRest.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
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
    @Transactional
    public Optional<Role> get(long id) {
        return roleDAO.get(id);
    }

    @Override
    @Transactional
    public Optional<Set<Role>> getAll() {
        return roleDAO.getAll();
    }


    @Override
    @Transactional
    public void add(Role role) {
        roleDAO.add(role);
    }

    @Override
    @Transactional
    public void update(Role role) {
        roleDAO.update(role);
    }

    @Override
    @Transactional
    public void delete(long id) {
        roleDAO.delete(id);
    }
}
