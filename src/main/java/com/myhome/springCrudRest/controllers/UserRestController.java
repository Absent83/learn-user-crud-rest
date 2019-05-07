package com.myhome.springCrudRest.controllers;

import com.myhome.springCrudRest.model.Role;
import com.myhome.springCrudRest.model.User;
import com.myhome.springCrudRest.model.dto.UserForm;
import com.myhome.springCrudRest.service.RoleService;
import com.myhome.springCrudRest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;


@RestController
public class UserRestController {

    @Autowired
    UserService userService;

    @Autowired
    RoleService roleService;


    @GetMapping(path = "/api/users")
    public List<User> getAllUsers(@RequestParam(name = "username", required = false) String username){

        List<User> users = null;

        if (username == null || username.isEmpty()){
            users = userService.getAll();
        }
        else {
            users = Collections.singletonList(userService.getByUsername(username).orElseThrow(IllegalArgumentException::new));
        }

        return users;
    }


    @PostMapping(path="/api/users")
    public ResponseEntity<Object> addUser(@RequestBody User userForm) { //
        //System.out.println(userForm.getName());

//        User userNew = new User();
//        userNew.setName(userForm.getName());
//        userNew.setEmail(userForm.getEmail());

        userService.add(userForm);
        return ResponseEntity.ok().build();
    }


    @GetMapping(path = "/api/users/{user-id}")
    public User getUser(@PathVariable("user-id") Integer userId){
        Optional<User> userCandidate = userService.get(userId);

        User user = null;

        if (userCandidate.isPresent()){
            user = userCandidate.get();
        }
        else {
            throw (new IllegalArgumentException());
        }
        return user;
    }


    @PutMapping(path = "/api/users/{user-id}")
    public User updateUser(@PathVariable("user-id") Integer userId, @RequestBody UserForm userForm) {

        System.out.println("username: " + userForm.getUsername() + "" +
                "firstname:" + userForm.getFirstName() + ""
        );

        User user = userService.get(userId).orElseThrow(IllegalArgumentException::new);

        user.setUsername(userForm.getUsername());
        user.setFirstName(userForm.getFirstName());
        user.setEmail(userForm.getEmail());
        user.setPassword(userForm.getPassword());

        Set<Role> roles = new HashSet<>();

        for (Integer roleId : userForm.getRoles()) {
            roles.add(roleService.get(roleId).orElseThrow(IllegalArgumentException::new));
        }
        user.setRoles(roles);

        userService.update(user);

        return user;
    }


    @DeleteMapping(path = "/api/users/{user-id}")
    public ResponseEntity<Object> deleteUser(@PathVariable("user-id") Integer userId){

        Optional<User> userCandidate = userService.get(userId);

        if (userCandidate.isPresent()){
            userService.delete(userId);
        } else {
            throw (new IllegalArgumentException());
        }
        return ResponseEntity.ok().build();
    }
}
