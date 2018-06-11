package com.bigdata.project.weatherdata.ws.process;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;
import org.springframework.util.DefaultPropertiesPersister;

@Component
public class PropertyHandler {
	
	private static final String FILE_NAME = "/tmp/bigdata/weatherdataws/run.properties";
	
	@Autowired
	private ResourceLoader resourceLoader;
	
	Properties properties = null;
	
	public void storeProperties() throws FileNotFoundException, IOException, URISyntaxException {
		DefaultPropertiesPersister defaultPropertiesPersister = new DefaultPropertiesPersister();
		//File file = new File(getClass().getClassLoader().getResource(FILE_NAME).getFile());
		File file = new File(FILE_NAME);
		defaultPropertiesPersister.store(properties, new FileOutputStream(file), null);
	}
	
	public Properties loadProperties() throws IOException {
		properties = new Properties();
		//properties.load(new FileInputStream(new File(getClass().getClassLoader().getResource(FILE_NAME).getFile())));
		properties.load(new FileInputStream(new File(FILE_NAME)));
		return properties;
	}
	
	public Properties getProperties() throws IOException {
		if(properties != null) {
			return properties;
		}
		return loadProperties();
	}

}
