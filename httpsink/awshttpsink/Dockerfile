#It is mandatory to pass AWS_S3_BUCKET & AWS_REGION & AWS_ACCESSKEY & AWS_SECRETKEY
FROM openjdk:8
LABEL maintainer="sagarksuchi@gmail.com"
LABEL author="Winter Soldier"
LABEL description="Spring S3 HTTP REST service that injects data into AWS s3 bucket"
RUN mkdir /opt/kafkaconsumer && \
    mkdir /tmp/downloads && \
    mkdir /tmp/downloads/sink && \
    mkdir /tmp/downloads/scripts
COPY target/awshttpsink-1.0.0-SNAPSHOT.jar /tmp/downloads/sink/httpsink.jar
COPY bootstrap.sh /tmp/downloads/scripts/
ENV PATH="/tmp/scripts:${PATH}"
ENTRYPOINT [ "bash", "/tmp/downloads/scripts/bootstrap.sh" ]
EXPOSE 7075