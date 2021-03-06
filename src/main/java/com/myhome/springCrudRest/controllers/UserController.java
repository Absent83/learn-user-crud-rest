package com.myhome.springCrudRest.controllers;

import com.myhome.springCrudRest.model.User;
import com.myhome.springCrudRest.service.RoleService;
import com.myhome.springCrudRest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author Nick Dolgopolov (nick_kerch@mail.ru; https://github.com/Absent83/)
 */

@Controller
public class UserController {

    private final
    UserService userService;

    private final
    RoleService roleService;

    @Autowired
    public UserController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }


    @GetMapping(path = "/users/list")
    public ModelAndView getAllUsersList() {

        List<User> users = userService.getAll();

        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.setViewName("list");
        modelAndView.setViewName("adminpage");
        modelAndView.addObject("usersFromServer", users);
        modelAndView.addObject("rolesFromServer", roleService.getAll());

        return modelAndView;
    }

//
//    @GetMapping(path = "/users/edit")
//    public ModelAndView editUserForm(@RequestParam(name = "userId", required = true) Long userId) {
//
//        Optional<User> userCandidate = userService.get(userId);
//
//        User user = userCandidate.orElseThrow(IllegalArgumentException::new);
//
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.setViewName("edit");
//        modelAndView.addObject("userFromServer", user);
//        modelAndView.addObject("rolesFromServer", roleService.getAll());
//
//        return modelAndView;
//    }


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

        modelAndView.addObject("rolesFromServer", roleService.getAll());

        return modelAndView;
    }


    @PostMapping(path = "/users/add")
    public String addUserSubmit(User user) {

        userService.add(user);

        return "redirect:/users/list";

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
