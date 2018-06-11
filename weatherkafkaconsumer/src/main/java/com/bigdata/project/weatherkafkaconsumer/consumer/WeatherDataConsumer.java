package com.bigdata.project.weatherkafkaconsumer.consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@PropertySource("classpath:application.properties")
@Component
public class WeatherDataConsumer {

	@Value(value = "${userlog.topic.name}")
	private String topicName;

	@Autowired
	private ElasticSearchClient elasticSearchClient;

	@KafkaListener(topics = "${userlog.topic.name}", containerFactory = "kafkaListenerContainerFactory")
	public void listenWithHeaders(@Payload String message) {
		elasticSearchClient.indexRecord(message);
	}
}
