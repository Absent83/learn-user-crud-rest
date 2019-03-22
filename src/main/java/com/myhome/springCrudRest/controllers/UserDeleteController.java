package com.myhome.springCrudRest.controllers;

import com.myhome.springCrudRest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Nick Dolgopolov (nick_kerch@mail.ru; https://github.com/Absent83/)
 */
@Controller
public class UserDeleteController implements org.springframework.web.servlet.mvc.Controller {

    @Autowired
    private UserService userService;

    @Override
    @RequestMapping(path = "/users/delete")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        if (request.getMethod().equals("POST")){

            long userId = Long.parseLong(request.getParameter("userId"));

            userService.delete(userId); //todo взять id в request

            RedirectView redirectView = new RedirectView("/users/list");
            //redirectView.setStatusCode(HttpStatus.MOVED_PERMANENTLY);
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.setView(redirectView);

            return modelAndView;
        }
        return null;
    }
}
