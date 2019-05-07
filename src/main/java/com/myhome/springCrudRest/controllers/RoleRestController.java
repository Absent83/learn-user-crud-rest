package com.myhome.springCrudRest.controllers;

import com.myhome.springCrudRest.model.Role;
import com.myhome.springCrudRest.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;


@RestController
public class RoleRestController {

    @Autowired
    RoleService roleService;


    @GetMapping(path = "/api/roles")
    public List<Role> getAllRoles(@RequestParam(name = "authority", required = false) String authority) {

        List<Role> roles = null;

        if (authority == null || authority.isEmpty()) {
            roles = roleService.getAll();
        } else {
            roles = Collections.singletonList(roleService.getByAuthority(authority).orElseThrow(IllegalArgumentException::new));
        }

        return roles;
    }


}
