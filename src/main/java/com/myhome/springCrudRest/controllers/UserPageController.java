package com.myhome.springCrudRest.controllers;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Nick Dolgopolov (nick_kerch@mail.ru; https://github.com/Absent83/)
 * created on 04.04.2019
 */

@Controller
public class UserPageController {

    @GetMapping(path = "/hallo")
    public ModelAndView getUserPage() {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("hallo");

        modelAndView.addObject("userAuthorizedLogin", SecurityContextHolder.getContext().getAuthentication().getPrincipal());

        return modelAndView;
    }
}
