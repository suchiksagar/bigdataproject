package com.bigdata.project.weatherdata.ws.process;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.core.io.ResourceLoader;

import com.bigdata.project.weatherdata.ws.client.OpenWeatherMapApiClient;

public class DataProcessor implements Runnable {

	private OpenWeatherMapApiClient client;
	private PropertyHandler propertyHandler;
	private DataWriter dataWriter;
	private ResourceLoader resourceLoader;

	private static final String CITY_INDEX = "weatherdata.app.lastcityindex";
	private static final String FILE_NAME = "cityids.txt";
	private Integer cityIndex = -1;

	private String [] CITY_LIST;

	public DataProcessor(OpenWeatherMapApiClient client, PropertyHandler propertyHandler, DataWriter dataWriter, ResourceLoader resourceLoader) throws IOException, URISyntaxException {
		this.client = client;
		this.propertyHandler = propertyHandler;
		this.dataWriter = dataWriter;
		this.resourceLoader = resourceLoader;
		this.CITY_LIST = loadCityList();
	}

	@Override
	public void run() {
		try {
			String city = getCity();
			//System.out.println("Getting response fr city: "+ city);
			String response = client.getData(city);
			//System.out.println(response);
			dataWriter.publishResponse(response);
			propertyHandler.getProperties().put(CITY_INDEX, cityIndex.toString());
			propertyHandler.storeProperties();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	protected String getCity() throws NumberFormatException, IOException {
		cityIndex = Integer.parseInt(propertyHandler.getProperties().getProperty(CITY_INDEX));
		if (cityIndex == (CITY_LIST.length - 1)) {
			cityIndex = -1;
		}
		cityIndex++;
		return CITY_LIST[cityIndex];
	}
	
	protected String[] loadCityList() throws IOException, URISyntaxException {
		InputStream is = this.getClass().getClassLoader().getResourceAsStream(FILE_NAME);
		List<String> cities =  new BufferedReader(new InputStreamReader(is,
		          StandardCharsets.UTF_8)).lines().collect(Collectors.toList());
		return cities.toArray(new String[cities.size()]);
	}

}
