package com.myhome.springCrudRest.controllers;

import com.myhome.springCrudRest.model.User;
import com.myhome.springCrudRest.service.RoleService;
import com.myhome.springCrudRest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;

/**
 * @author Nick Dolgopolov (nick_kerch@mail.ru; https://github.com/Absent83/)
 */

@Controller
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    RoleService roleService;


    @GetMapping(path = "/users/list")
    public ModelAndView getAllUsersList() {

        Optional<List<User>> usersCandidate;
        usersCandidate = userService.getAll();

        List<User> users = null;

        if (usersCandidate.isPresent()) {
            users = usersCandidate.get();
        } else {
            throw (new IllegalArgumentException());
        }


        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("list");
        modelAndView.addObject("usersFromServer", users);

        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        modelAndView.addObject("userAuthorizedLogin", userDetails.getUsername());

        return modelAndView;
    }


    @GetMapping(path = "/users/edit")
    public ModelAndView editUserForm(@RequestParam(name = "userId", required = true) Long userId) {

        Optional<User> userCandidate = userService.get(userId);

        User user = userCandidate.get();

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("edit");
        modelAndView.addObject("userFromServer", user);
        modelAndView.addObject("rolesFromServer", roleService.getAll().orElseThrow(IllegalArgumentException::new)); //todo тут какой Exception?

        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        modelAndView.addObject("userAuthorizedLogin", userDetails.getUsername());

        return modelAndView;
    }


    @PostMapping(path = "/users/edit")
    public ModelAndView editUserSubmit(User user) {

        userService.update(user);

        RedirectView redirectView = new RedirectView("/users/list");
        //redirectView.setStatusCode(HttpStatus.MOVED_PERMANENTLY);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setView(redirectView);

        return modelAndView;
    }


    @GetMapping(path = "/users/add")
    public ModelAndView addUserForm() {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("add");

        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        modelAndView.addObject("userAuthorizedLogin", userDetails.getUsername());
        modelAndView.addObject("rolesFromServer", roleService.getAll().orElseThrow(IllegalArgumentException::new)); //todo тут какой Exception?

        return modelAndView;
    }


    @PostMapping(path = "/users/add")
    public String addUserSubmit(User user) {

        userService.add(user);

        return "redirect:/users/list"; //todo в чем разница этих двух редиректов

//        RedirectView redirectView = new RedirectView("/users/list");
//        //redirectView.setStatusCode(HttpStatus.MOVED_PERMANENTLY);
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.setView(redirectView);
//
//        return modelAndView;
    }

    @RequestMapping(path = "/users/delete")
    public ModelAndView deleteUser(HttpServletRequest request, HttpServletResponse response) {
        if (request.getMethod().equals("POST")){

            long userId = Long.parseLong(request.getParameter("userId"));

            userService.delete(userId);

            RedirectView redirectView = new RedirectView("/users/list");
            //redirectView.setStatusCode(HttpStatus.MOVED_PERMANENTLY);
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.setView(redirectView);

            return modelAndView;
        }
        return null;
    }
}
