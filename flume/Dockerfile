#It is mandatory to pass FLUME_AGENT_NAME & DOCKER_HOST
FROM probablyfine/flume
LABEL maintainer="sagarksuchi@gmail.com"
LABEL author="Winter Soldier"
LABEL description="Flume Agent that polls filepollws and dumps it to kafka consumer"
RUN apt-get update
RUN apt-get install -y curl
RUN mkdir /tmp/dump && \
    mkdir /tmp/flumefeed && \
    mkdir /tmp/scripts
COPY flume-kafka-hdfs-conf.properties /opt/flume-config/flume.conf
COPY bootstrap.sh /tmp/scripts/
ENV PATH="/opt/flume/bin:/tmp/scripts:${PATH}"
WORKDIR /opt/flume/bin
ENTRYPOINT [ "bash",  "/tmp/scripts/bootstrap.sh"]
