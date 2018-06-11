package com.bigdata.project.weatherdata.ws.process;

import java.io.IOException;
import java.net.URISyntaxException;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.bigdata.project.weatherdata.ws.WeatherdatawsApplication;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {WeatherdatawsApplication.class})
public class DataWriterTest {
	
	@Autowired
	private DataWriter dataWriter;
	
	@Autowired
	private ResourceLoader resourceLoader;
	
	@Ignore
	@Test
	public void writeTest() throws IOException {
		dataWriter.publishResponse("This is sample response");
	}
	
	@Ignore
	@Test
	public void readCitiestest() throws IOException, URISyntaxException {
		new DataProcessor(null, null, dataWriter, resourceLoader);
	}
}
