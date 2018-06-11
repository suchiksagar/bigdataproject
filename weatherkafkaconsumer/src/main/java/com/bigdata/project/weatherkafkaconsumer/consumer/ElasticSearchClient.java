package com.bigdata.project.weatherkafkaconsumer.consumer;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Component
@PropertySource("classpath:application.properties")
public class ElasticSearchClient {

	RestTemplate restTemplate = new RestTemplate();

	@Value(value = "${elastic.bootstrapAddress}")
	public String IPADDRESS;
	private final String ENDPOINT = "http://" + IPADDRESS + ":9200";
	private static final String INDEX = "/weather/current/";

	public String indexRecord(String data) {
		Long currentTime = System.currentTimeMillis();
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
		headers.add("Content-Type", MediaType.APPLICATION_JSON.toString());
		HttpEntity<String> request = new HttpEntity<String>(data, headers);
		ResponseEntity<String> response = restTemplate.exchange(ENDPOINT + INDEX + currentTime.toString(),
				HttpMethod.POST, request, String.class);
		return response.getBody();
	}
}
