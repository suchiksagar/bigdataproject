package com.bigdata.project.weatherdata.ws;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Properties;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.bigdata.project.weatherdata.ws.process.PropertyHandler;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {WeatherdatawsApplication.class})
public class WeatherdatawsApplicationTests {

	@Autowired
	private PropertyHandler propertyHandler;
	
	@Test
	public void contextLoads() throws IOException, URISyntaxException {
		Properties properties = propertyHandler.getProperties();
		System.out.println(properties.getProperty("weatherdata.app.lastcityindex"));
		properties.put("weatherdata.app.lastcityindex", new Integer(9).toString());
		propertyHandler.storeProperties();
	}

}
