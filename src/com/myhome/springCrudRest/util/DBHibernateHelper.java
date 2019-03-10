package com.myhome.springCrudRest.util;

import com.myhome.springCrudRest.model.UserDataSet;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class DBHibernateHelper {

	public static SessionFactory createSessionFactory(Configuration configuration) {
		StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
		builder.applySettings(configuration.getProperties());
		ServiceRegistry serviceRegistry = builder.build();
		return configuration.buildSessionFactory(serviceRegistry);
	}



	public static Configuration getConfiguration() {
		Configuration configuration = new Configuration();
		configuration.addAnnotatedClass(UserDataSet.class);
		
		PropertiesReader propertiesReader = new PropertiesReader("hibernate.properties");
		
		configuration.setProperty("hibernate.dialect", propertiesReader.getProperties("dialect"));
		configuration.setProperty("hibernate.connection.driver_class", propertiesReader.getProperties("driver.class"));
		configuration.setProperty("hibernate.connection.url", propertiesReader.getProperties("connection.url"));
		configuration.setProperty("hibernate.connection.username", propertiesReader.getProperties("username"));
		configuration.setProperty("hibernate.connection.password", propertiesReader.getProperties("password"));
		configuration.setProperty("hibernate.show_sql", propertiesReader.getProperties("show_sql"));
		configuration.setProperty("hibernate.hbm2ddl.auto", propertiesReader.getProperties("hbm2ddl.auto"));
		return configuration;
	}
}
