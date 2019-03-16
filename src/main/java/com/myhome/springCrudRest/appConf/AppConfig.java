package com.myhome.springCrudRest.appConf;

import com.myhome.springCrudRest.model.User;
import com.myhome.springCrudRest.util.PropertiesReader;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.myhome.springCrudRest")
public class AppConfig implements WebMvcConfigurer {


    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        System.out.println("=== AppConfig ===" + "=== addViewControllers ===");
        registry.addViewController("/test");
    }

    @Bean
    public ViewResolver viewResolver() {
        System.out.println("=== AppConfig ===" + "=== viewResolver ===");

        InternalResourceViewResolver bean = new InternalResourceViewResolver();

        bean.setViewClass(JstlView.class);
        bean.setPrefix("/jsp/");
        bean.setSuffix(".jsp");

        return bean;
    }


    @Bean
    public EntityManagerFactory entityManagerFactory(){
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("aaasssddd");
        return entityManagerFactory;
    }


    @Bean
    public SessionFactory sessionFactory(org.hibernate.cfg.Configuration configuration)  {
        System.out.println("=== AppConfig ===" + "=== sessionFactory ===");

        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
        builder.applySettings(configuration.getProperties());
        ServiceRegistry serviceRegistry = builder.build();
        return configuration.buildSessionFactory(serviceRegistry);
    }


    @Bean
    public org.hibernate.cfg.Configuration configuration()  {
        System.out.println("=== AppConfig ===" + "=== configuration ===");

        org.hibernate.cfg.Configuration configuration = new org.hibernate.cfg.Configuration();
        configuration.addAnnotatedClass(User.class);

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
