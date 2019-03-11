package com.myhome.springCrudRest;

import com.myhome.springCrudRest.model.UserDAO;
import com.myhome.springCrudRest.model.UserDAOHibernate;
import com.myhome.springCrudRest.model.UserDataSet;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class Run {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        UserDAO userDAO = new UserDAOHibernate();

        UserDataSet user = new UserDataSet();
        user.setName("vasya");


        userDAO.add(user);
    }
}
