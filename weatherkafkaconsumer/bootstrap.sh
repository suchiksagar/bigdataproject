#!/bin/sh
#run the kafka console util to create topic
/tmp/downloads/kafka/bin/kafka-topics.sh --create --zookeeper ${DOCKER_HOST}:2181 --replication-factor 1 --partitions 2 --topic ${KAFKA_TOPIC}
echo "Created Topic, will kick off consumer"
java -jar /opt/kafkaconsumer/weatherkafkaconsumer.jar --kafka.bootstrapAddress=${DOCKER_HOST} --elastic.bootstrapAddress=${DOCKER_HOST} --userlog.topic.name=${KAFKA_TOPIC}