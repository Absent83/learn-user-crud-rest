package com.myhome.springCrudRest.controllers;

import com.myhome.springCrudRest.model.User;
import com.myhome.springCrudRest.service.UserService;
import net.bytebuddy.implementation.bytecode.Throw;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;


public class TestController {

    @Autowired
    UserService userService;

    @RequestMapping(path = "/test", method = RequestMethod.GET)
    public ModelAndView TestModelView(){


        Optional<User> userCandidate = userService.get(1);

        User user = null;

        if (userCandidate.isPresent()){
            user = userCandidate.get();
        }
        else {
            throw (new IllegalArgumentException());
        }

        System.out.println(user.getId() + " " + user.getName());


        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("test"); //имя view (не полный путь!! без расширения!)
        return modelAndView;
    }
}
