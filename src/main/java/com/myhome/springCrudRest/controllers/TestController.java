package com.myhome.springCrudRest.controllers;

import com.myhome.springCrudRest.model.User;
import com.myhome.springCrudRest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;


@Controller
public class TestController {

    @Autowired
    UserService userService;

    @RequestMapping(path = "/test", method = RequestMethod.GET)
    public ModelAndView TestModelView(){

        //Optional<User> userCandidate = userService.get(1);
        Optional<User> userCandidate = userService.getByName("ddd");




        User user = null;

        if (userCandidate.isPresent()){
            user = userCandidate.get();
        }
        else {
            throw (new IllegalArgumentException());
        }



        user.setName("AAAAAAAAAAAAAAA");

        userService.update(user);


        System.out.println(user.getId() + " " + user.getName());

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("test1"); //имя view (не полный путь!! без расширения!!)
        return modelAndView;
    }
}
