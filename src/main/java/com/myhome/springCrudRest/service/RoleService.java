package com.myhome.springCrudRest.service;

import com.myhome.springCrudRest.model.Role;

import java.util.Optional;
import java.util.Set;

public interface RoleService {
    Optional<Role> get(long id);

    Optional<Set<Role>> getAll();
    Optional<Set<Role>> getByUserId(long id);

    void add(Role role);
    void update(Role role);
    void delete(long id);
}

