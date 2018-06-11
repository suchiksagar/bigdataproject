package com.bigdata.project.weatherkafkaconsumer.consumer;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.kafka.KafkaAutoConfiguration;

@SpringBootApplication
@EnableAutoConfiguration(exclude = {KafkaAutoConfiguration.class})
public class App implements CommandLineRunner {
	public static void main(String[] args) {
		System.out.println("Running WeatherData Kafka Consumer... :");
		SpringApplication.run(App.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		for(int i =0; i<args.length; i++) {
			System.out.println("Argument "+ i+ ": "+args[i]);
		}		
	}
}
