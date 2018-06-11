package com.bigdata.project.weatherdata.ws.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Component
@PropertySource("classpath:application.properties")
public class OpenWeatherMapApiClient {

	private static final String BASE_URL = "http://api.openweathermap.org/data/2.5/weather";
	@Value(value = "${APIKEY}")
	private String KEY;

	@Autowired
	private RestTemplate restTemplate;

	public String getData(String city) {
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
		HttpEntity<?> entity = new HttpEntity<>(headers);
		HttpEntity<String> response = restTemplate.exchange(getBuilder(city).build().encode().toUri(), HttpMethod.GET,
				entity, String.class);
		return response.getBody();
	}

	protected UriComponentsBuilder getBuilder(String city) {
		return UriComponentsBuilder.fromHttpUrl(BASE_URL).queryParam("appid", KEY)
				.queryParam("id", city);
	}
}
