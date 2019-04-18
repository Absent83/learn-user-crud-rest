package com.myhome.springCrudRest.service;

import com.myhome.springCrudRest.dao.RoleDAO;
import com.myhome.springCrudRest.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * @author Nick Dolgopolov (nick_kerch@mail.ru; https://github.com/Absent83/)
 */

//@Service
@Component
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDAO roleDAO;

    @Override
    @Transactional(readOnly = true)
    public Optional<Role> get(long id) {
        return roleDAO.get(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Role> getAll() {
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
