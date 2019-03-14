package com.myhome.springCrudRest;

import com.myhome.springCrudRest.appConf.AppConfig;
import com.myhome.springCrudRest.model.UserDAO;
import com.myhome.springCrudRest.model.UserDataSet;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class Run {
    public static void main(String[] args) {
        //ApplicationContext applicationContext = new AnnotationConfigApplicationContext();


        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);


        UserDAO userDAO = applicationContext.getBean("userDAOHibernate", UserDAO.class);

        UserDataSet user = new UserDataSet();
        user.setName("vasya111");


        userDAO.add(user);
    }
}
