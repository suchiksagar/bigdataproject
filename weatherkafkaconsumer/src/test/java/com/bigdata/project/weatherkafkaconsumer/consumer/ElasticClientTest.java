package com.bigdata.project.weatherkafkaconsumer.consumer;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ElasticClientTest {
	
	private String data  = "{\"coord\":{\"lon\":-97.11,\"lat\":32.74},\"weather\":[{\"id\":800,\"main\":\"Clear\",\"description\":\"clear sky\",\"icon\":\"01d\"}],\"base\":\"stations\",\"main\":{\"temp\":294.44,\"pressure\":1027,\"humidity\":14,\"temp_min\":293.15,\"temp_max\":295.15},\"visibility\":16093,\"wind\":{\"speed\":3.6,\"deg\":280},\"clouds\":{\"all\":1},\"dt\":1512935700,\"sys\":{\"type\":1,\"id\":2555,\"message\":0.1804,\"country\":\"US\",\"sunrise\":1512912042,\"sunset\":1512948172},\"id\":4671240,\"name\":\"Arlington\",\"cod\":200}";
	
	@Test
	public void testClient() {
		//ElasticSearchClient client = new AnnotationConfigApplicationContext(KafkaConsumerConfig.class).getBean(ElasticSearchClient.class);
		ElasticSearchClient client = new ElasticSearchClient();
		client.IPADDRESS="ah-skshiras.dhcp.mathworks.com";
		System.out.println(client.indexRecord(data));
	}

}
