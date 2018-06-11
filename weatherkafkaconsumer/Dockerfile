#It is mandatory to pass DOCKER_HOST & KAFKA_TOPIC
FROM openjdk:8
LABEL maintainer="sagarksuchi@gmail.com"
LABEL author="Winter Soldier"
LABEL description="Spring Kafka Consumer connects Kafka & Elastic Search"
RUN mkdir /opt/kafkaconsumer && \
    mkdir /tmp/downloads && \
    mkdir /tmp/downloads/scripts && \
    cd /tmp/downloads && \
    curl http://apache.claz.org/kafka/1.1.0/kafka_2.11-1.1.0.tgz  > /tmp/downloads/kafka_2.11-1.1.0.tgz && \
    cd /tmp/downloads && \
    tar -xzf kafka_2.11-1.1.0.tgz && \
    mv /tmp/downloads/kafka_2.11-1.1.0 /tmp/downloads/kafka
COPY target/weatherkafkaconsumer-1.0.0-SNAPSHOT.jar /opt/kafkaconsumer/weatherkafkaconsumer.jar
COPY bootstrap.sh /opt/kafkaconsumer/
ENV PATH="/tmp/downloads/scripts:/tmp/downloads/kafka/bin:${PATH}"
WORKDIR /opt/kafkaconsumer
ENTRYPOINT [ "bash", "/opt/kafkaconsumer/bootstrap.sh"]
