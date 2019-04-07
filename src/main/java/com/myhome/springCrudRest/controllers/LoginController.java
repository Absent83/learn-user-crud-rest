package com.myhome.springCrudRest.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Nick Dolgopolov (nick_kerch@mail.ru; https://github.com/Absent83/)
 * created on 03.04.2019
 */

@Controller
public class LoginController {

    @GetMapping(path = "/login")
    public String getLoginPage(ModelMap model, HttpServletRequest httpServletRequest) {

        if (httpServletRequest.getParameterMap().containsKey("error")){
            model.addAttribute("error", "true");
        }

        return "login";
    }
}
