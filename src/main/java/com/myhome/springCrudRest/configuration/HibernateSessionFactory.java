package com.myhome.springCrudRest.configuration;

import com.myhome.springCrudRest.model.User;
import com.myhome.springCrudRest.util.PropertiesReader;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;

/**
 * @author Nick Dolgopolov (nick_kerch@mail.ru; https://github.com/Absent83/)
 */
public class HibernateSessionFactory {

    //@Bean
    public SessionFactory sessionFactory(org.hibernate.cfg.Configuration configuration) {
        System.out.println("=== AppConfig ===" + "=== hibernate sessionFactory ===");

        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
        builder.applySettings(configuration.getProperties());
        ServiceRegistry serviceRegistry = builder.build();
        return configuration.buildSessionFactory(serviceRegistry);
    }


    //@Bean
    public org.hibernate.cfg.Configuration configuration() {
        System.out.println("=== AppConfig ===" + "=== hibernate configuration ===");

        org.hibernate.cfg.Configuration configuration = new org.hibernate.cfg.Configuration();
        configuration.addAnnotatedClass(User.class);

        PropertiesReader propertiesHibernateReader = new PropertiesReader("hibernate.properties");

        configuration.setProperty("hibernate.dialect", propertiesHibernateReader.getProperties("dialect"));
        configuration.setProperty("hibernate.show_sql", propertiesHibernateReader.getProperties("show_sql"));
        configuration.setProperty("hibernate.hbm2ddl.auto", propertiesHibernateReader.getProperties("hbm2ddl.auto"));
        configuration.setProperty("hibernate.id.new_generator_mappings", propertiesHibernateReader.getProperties("id.new_generator_mappings"));


        PropertiesReader propertiesDataSourceReader = new PropertiesReader("mysqlDataSource.properties");

        configuration.setProperty("hibernate.connection.driver_class", propertiesDataSourceReader.getProperties("driver.class"));
        configuration.setProperty("hibernate.connection.url", propertiesDataSourceReader.getProperties("connection.url"));
        configuration.setProperty("hibernate.connection.username", propertiesDataSourceReader.getProperties("username"));
        configuration.setProperty("hibernate.connection.password", propertiesDataSourceReader.getProperties("password"));
        return configuration;
    }
}
