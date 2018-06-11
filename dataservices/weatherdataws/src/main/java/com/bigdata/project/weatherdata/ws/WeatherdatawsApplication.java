package com.bigdata.project.weatherdata.ws;

import java.io.IOException;
import java.util.Iterator;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.DeserializationFeature;

@SpringBootApplication
public class WeatherdatawsApplication {
	

	public static void main(String[] args) {
		SpringApplication.run(WeatherdatawsApplication.class, args);
	}
	
	@Bean
	public RestTemplate restTemplate() {
	  RestTemplate restTemplate = new RestTemplate();
	  this.configureConverters(restTemplate);
	  return restTemplate;
	}

	private void configureConverters(RestTemplate restTemplate) {
	  Iterator var2 = restTemplate.getMessageConverters().iterator();
	  while(var2.hasNext()) {
	    HttpMessageConverter<?> converter = (HttpMessageConverter)var2.next();
	    if (converter instanceof MappingJackson2HttpMessageConverter) {
	      ((MappingJackson2HttpMessageConverter)converter).getObjectMapper().setSerializationInclusion(Include.NON_NULL).configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	    }
	  }
	}
}
