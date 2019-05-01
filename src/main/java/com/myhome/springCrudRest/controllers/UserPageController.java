package com.myhome.springCrudRest.controllers;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Nick Dolgopolov (nick_kerch@mail.ru; https://github.com/Absent83/)
 * created on 04.04.2019
 */

@Controller
public class UserPageController {

    @GetMapping(path = "/profile")
    public ModelAndView getUserPage() {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("profile");

        modelAndView.addObject("userAuthorizedLogin", SecurityContextHolder.getContext().getAuthentication().getPrincipal());

        return modelAndView;
    }
}
