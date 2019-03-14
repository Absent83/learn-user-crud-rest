package com.myhome.springCrudRest.util;


import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesReader {
	private final String fileName;

	public PropertiesReader(String fileName) {
		this.fileName = fileName;
	}


	public String getProperties(String name) {


		String value = null;
		Properties properties = new Properties();
		PropertiesReader reader = new PropertiesReader(fileName);
		try(InputStream input = reader.getClass().getClassLoader().getResourceAsStream(fileName)){
			properties.load(input);
			value = properties.getProperty(name);
		} catch (IOException e) {
			System.out.println("Can`t read properties file");
		}
		return value;
	}
}
