package com.myhome.springCrudRest.controllers;

import com.myhome.springCrudRest.form.UserForm;
import com.myhome.springCrudRest.model.User;
import com.myhome.springCrudRest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
public class TestController {

    @Autowired
    UserService userService;


    @GetMapping(path = "/users")
    public List<User> getAllUsers(@RequestParam(name = "username", required = false) String userName){

        Optional<List<User>> usersCandidate;

        if (userName == null || userName.isEmpty()){
            usersCandidate = userService.getAll();
        }
        else {
            usersCandidate = userService.getByName(userName);
        }

        List<User> users = null;

        if (usersCandidate.isPresent()){
            users = usersCandidate.get();
        }
        else {
            throw (new IllegalArgumentException()); //todo тут какой exception кидать?
        }
        return users;
    }

    @PostMapping(path="/users")
    public ResponseEntity<Object> addUser(@RequestBody UserForm userForm) {
        System.out.println(userForm.getName());

        User userNew = new User();
        userNew.setName(userForm.getName());
        userNew.setEmail(userForm.getEmail());

        userService.add(userNew);
        return ResponseEntity.ok().build();
    }


    @GetMapping(path = "/users/{user-id}")
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


    @PutMapping(path = "/users/{user-id}")
    public ResponseEntity<Object> updateUser(@PathVariable("user-id") Integer userId, @RequestBody UserForm userForm){
        Optional<User> userCandidate = userService.get(userId);

        User user = null;

        if (userCandidate.isPresent()){
            user = userCandidate.get();
            user.setName(userForm.getName());
            user.setEmail(userForm.getEmail());
            userService.update(user);
        }
        else {
            throw (new IllegalArgumentException()); //todo тут какой exception кидать?
        }
        return ResponseEntity.ok().build();
    }


    @DeleteMapping(path = "/users/{user-id}")
    public ResponseEntity<Object> deleteUser(@PathVariable("user-id") Integer userId){

        Optional<User> userCandidate = userService.get(userId);

        User user = null;

        if (userCandidate.isPresent()){
            userService.delete(userId);
        }
        else {
            throw (new IllegalArgumentException());
        }
        return ResponseEntity.ok().build();
    }
}
