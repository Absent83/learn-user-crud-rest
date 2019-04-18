package com.myhome.springCrudRest.configuration;

import com.myhome.springCrudRest.converter.RoleConverter;
import com.myhome.springCrudRest.model.Role;
import com.myhome.springCrudRest.model.User;
import com.myhome.springCrudRest.util.PropertiesReader;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.format.FormatterRegistry;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;


@Configuration
@EnableWebMvc
@EnableAspectJAutoProxy
@EnableTransactionManagement
@ComponentScan(basePackages = "com.myhome.springCrudRest")
@Import({ SecurityConfig.class })
public class AppConfig implements WebMvcConfigurer/*, TransactionManagementConfigurer*/ {

    @Autowired
    RoleConverter roleConverter;

//    @Autowired
//    PlatformTransactionManager transactionManager;


    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        System.out.println("=== AppConfig ===" + "=== addViewControllers ===");
        registry.addViewController("/users");
        registry.addViewController("/login");
        registry.addViewController("/profile");
        registry.addViewController("/api"); //todo тут как правильно указывать?
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


    @Override
    public void addFormatters(FormatterRegistry registry) {
        System.out.println("=== AppConfig ===" + "=== addFormatters ===");
        registry.addConverter(roleConverter);
    }



    @Bean
    public PlatformTransactionManager transactionManager(){
        System.out.println("=== AppConfig ===" + "=== transactionManager ===");
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory( HibernateEntityManagerHolder.ENTITY_MANAGER_FACTORY );
        return transactionManager;
    }

//    @Override
//    public PlatformTransactionManager annotationDrivenTransactionManager() {
//        return transactionManager;
//    }

    private static class HibernateEntityManagerHolder {
        private static final HibernateEntityManagerFactory hemf = new HibernateEntityManagerFactory(
                new Class[]{
                        User.class,
                        Role.class
                });

        private static final EntityManagerFactory ENTITY_MANAGER_FACTORY = hemf.getEntityManagerFactory();
        private static final EntityManager ENTITY_MANAGER = ENTITY_MANAGER_FACTORY.createEntityManager();
    }

    @Bean
    public static EntityManager getHibernateEntityManager() {
        System.out.println("=== AppConfig ===" + "=== getHibernateEntityManager ===");
        return HibernateEntityManagerHolder.ENTITY_MANAGER;
    }

        @Bean
    public static EntityManagerFactory getHibernateEntityManagerFactory() {
        System.out.println("=== AppConfig ===" + "=== getHibernateEntityManagerFactory ===");
        return HibernateEntityManagerHolder.ENTITY_MANAGER_FACTORY;
    }


    //@Bean
    public SessionFactory sessionFactory(org.hibernate.cfg.Configuration configuration)  {
        System.out.println("=== AppConfig ===" + "=== hibernate sessionFactory ===");

        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
        builder.applySettings(configuration.getProperties());
        ServiceRegistry serviceRegistry = builder.build();
        return configuration.buildSessionFactory(serviceRegistry);
    }


    //@Bean
    public org.hibernate.cfg.Configuration configuration()  {
        System.out.println("=== AppConfig ===" + "=== hibernate configuration ===");

        org.hibernate.cfg.Configuration configuration = new org.hibernate.cfg.Configuration();
        configuration.addAnnotatedClass(User.class);

        PropertiesReader propertiesHibernateReader = new PropertiesReader("hibernate.properties");

        configuration.setProperty("hibernate.dialect", propertiesHibernateReader.getProperties("dialect"));
        configuration.setProperty("hibernate.show_sql", propertiesHibernateReader.getProperties("show_sql"));
        configuration.setProperty("hibernate.hbm2ddl.auto", propertiesHibernateReader.getProperties("hbm2ddl.auto"));
        //configuration.setProperty("hibernate.id.new_generator_mappings", propertiesReader.getProperties("id.new_generator_mappings"));


        PropertiesReader propertiesDataSourceReader = new PropertiesReader("mysqlDataSource.properties");

        configuration.setProperty("hibernate.connection.driver_class", propertiesDataSourceReader.getProperties("driver.class"));
        configuration.setProperty("hibernate.connection.url", propertiesDataSourceReader.getProperties("connection.url"));
        configuration.setProperty("hibernate.connection.username", propertiesDataSourceReader.getProperties("username"));
        configuration.setProperty("hibernate.connection.password", propertiesDataSourceReader.getProperties("password"));
        return configuration;
    }
}
