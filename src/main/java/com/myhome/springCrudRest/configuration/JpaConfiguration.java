package com.myhome.springCrudRest.configuration;

import com.myhome.springCrudRest.model.Role;
import com.myhome.springCrudRest.model.User;
import com.myhome.springCrudRest.util.PropertiesReader;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import org.hibernate.jpa.boot.internal.EntityManagerFactoryBuilderImpl;
import org.hibernate.jpa.boot.internal.PersistenceUnitInfoDescriptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.spi.PersistenceUnitInfo;
import javax.sql.DataSource;
import java.util.*;
import java.util.stream.Collectors;


@Configuration
@EnableAspectJAutoProxy
@EnableTransactionManagement
public class JpaConfiguration {

    private final Class[] entityClasses = new Class[]{
            User.class,
            Role.class
    };


    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory emf) {
        System.out.println("=== HibernateEntityManagerFactory ===" + "=== transactionManager ===");
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(emf);
        return transactionManager;
    }


    @Bean
    public EntityManager getEntityManager(EntityManagerFactory emf) {
        System.out.println("=== HibernateEntityManagerFactory === getEntityManager ===");
        return emf.createEntityManager();
    }


    @Bean
    public EntityManagerFactory getEntityManagerFactory() {
        System.out.println("=== HibernateEntityManagerFactory === getEntityManagerFactory ===");
        PersistenceUnitInfo persistenceUnitInfo = getPersistenceUnitInfo(getClass().getSimpleName());
        Map<String, Object> configuration = new HashMap<>();
        return new EntityManagerFactoryBuilderImpl(new PersistenceUnitInfoDescriptor(persistenceUnitInfo), configuration)
                .build();
    }


    private HibernatePersistenceUnitInfo getPersistenceUnitInfo(String name) {
        return new HibernatePersistenceUnitInfo(name, getEntityClassNames(), getProperties());
    }

    private List<String> getEntityClassNames() {
        return Arrays.stream(getEntities())
                .map(Class::getName)
                .collect(Collectors.toList());
    }

    private Properties getProperties() {
        PropertiesReader propertiesHibernateReader = new PropertiesReader("hibernate.properties");

        Properties properties = new Properties();

        properties.put("hibernate.dialect", propertiesHibernateReader.getProperties("dialect"));
        properties.put("hibernate.id.new_generator_mappings", propertiesHibernateReader.getProperties("id.new_generator_mappings"));
        properties.put("hibernate.show_sql", propertiesHibernateReader.getProperties("show_sql"));
        properties.put("hibernate.hbm2ddl.auto",  propertiesHibernateReader.getProperties("hbm2ddl.auto"));
        properties.put("hibernate.connection.datasource", getMysqlDataSource());
        return properties;
    }

    private Class[] getEntities() {
        return entityClasses;
    }

    private DataSource getMysqlDataSource() {
        MysqlDataSource mysqlDataSource = new MysqlDataSource();

        PropertiesReader propertiesDataSourceReader = new PropertiesReader("mysqlDataSource.properties");

        mysqlDataSource.setURL(propertiesDataSourceReader.getProperties("connection.url"));
        mysqlDataSource.setUser(propertiesDataSourceReader.getProperties("username"));
        mysqlDataSource.setPassword(propertiesDataSourceReader.getProperties("password"));
        return mysqlDataSource;
    }
}
