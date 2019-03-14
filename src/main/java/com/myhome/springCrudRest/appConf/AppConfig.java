package com.myhome.springCrudRest.appConf;

import com.myhome.springCrudRest.model.UserDataSet;
import com.myhome.springCrudRest.util.PropertiesReader;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "com.myhome.springCrudRest")
public class AppConfig {


    @Bean
    public SessionFactory sessionFactory(org.hibernate.cfg.Configuration configuration)  {
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
        builder.applySettings(configuration.getProperties());
        ServiceRegistry serviceRegistry = builder.build();
        return configuration.buildSessionFactory(serviceRegistry);
    }


    @Bean
    public org.hibernate.cfg.Configuration configuration()  {
        org.hibernate.cfg.Configuration configuration = new org.hibernate.cfg.Configuration();
        configuration.addAnnotatedClass(UserDataSet.class);

        PropertiesReader propertiesReader = new PropertiesReader("hibernate.properties");

        configuration.setProperty("hibernate.dialect", propertiesReader.getProperties("dialect"));
        configuration.setProperty("hibernate.connection.driver_class", propertiesReader.getProperties("driver.class"));
        configuration.setProperty("hibernate.connection.url", propertiesReader.getProperties("connection.url"));
        configuration.setProperty("hibernate.connection.username", propertiesReader.getProperties("username"));
        configuration.setProperty("hibernate.connection.password", propertiesReader.getProperties("password"));
        configuration.setProperty("hibernate.show_sql", propertiesReader.getProperties("show_sql"));
        configuration.setProperty("hibernate.hbm2ddl.auto", propertiesReader.getProperties("hbm2ddl.auto"));
        //configuration.setProperty("hibernate.serverTimezone", propertiesReader.getProperties("serverTimezone"));
        //configuration.setProperty("hibernate.useLegacyDatetimeCode", propertiesReader.getProperties("useLegacyDatetimeCode"));
        //configuration.setProperty("hibernate.useJDBCCompliantTimezoneShift", propertiesReader.getProperties("useJDBCCompliantTimezoneShift"));
        //configuration.setProperty("hibernate.useUnicode", propertiesReader.getProperties("useUnicode"));
        //configuration.setProperty("hibernate.id.new_generator_mappings", propertiesReader.getProperties("id.new_generator_mappings"));
        return configuration;
    }
}
