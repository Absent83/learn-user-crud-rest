package com.myhome.springCrudRest;

import com.myhome.springCrudRest.configuration.AppConfig;
import com.myhome.springCrudRest.dao.UserDAO;
import com.myhome.springCrudRest.dao.UserDAOHibernate;
import com.myhome.springCrudRest.model.User;


public class Run {
    public static void main(String[] args) {
        //ApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        //ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);

        AppConfig appConfig = new AppConfig();

        UserDAO userDAO = new UserDAOHibernate(appConfig.sessionFactory(appConfig.configuration()));

        User user = new User();
        user.setName("vasya111");

        userDAO.add(user);
    }
}
