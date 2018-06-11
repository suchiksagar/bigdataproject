package com.bigdata.project.weatherdata.ws.client;

import java.net.URI;

import org.junit.Test;

public class OpenWeatherMapapiClientTest {
	
	@Test
	public void testUrl() {
		URI url = new OpenWeatherMapApiClient().getBuilder("New York,US").build().toUri();
		System.out.println(url.toString());
	}

}
