package com.myhome.springCrudRest;

import com.myhome.springCrudRest.configuration.HibernateSessionFactory;
import com.myhome.springCrudRest.dao.UserDAO;
import com.myhome.springCrudRest.dao.UserDAOHibernate;
import com.myhome.springCrudRest.model.User;


public class Run {
    public static void main(String[] args) {
        //ApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        //ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);

        HibernateSessionFactory hibernateSessionFactory = new HibernateSessionFactory();

        UserDAO userDAO = new UserDAOHibernate(hibernateSessionFactory.sessionFactory(hibernateSessionFactory.configuration()));

        User user = new User();
        user.setFirstName("vasya111");

        userDAO.add(user);
    }
}
