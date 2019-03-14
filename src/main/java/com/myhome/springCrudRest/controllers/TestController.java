package com.myhome.springCrudRest.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


public class TestController {

    @RequestMapping(path = "/test", method = RequestMethod.GET)
    public ModelAndView TestModelView(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("test"); //имя view (не полный путь!! без расширения!)
        return modelAndView;
    }
}
