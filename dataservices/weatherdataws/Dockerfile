FROM openjdk:8
LABEL maintainer="sagarksuchi@gmail.com"
LABEL author="Winter Soldier"
LABEL description="Weather Data extractor connects to OpenWeatherMap API and downloads weather info"
RUN mkdir /tmp/bigdata
RUN mkdir /tmp/bigdata/weatherdataws
COPY run.properties /tmp/bigdata/weatherdataws/
COPY target/weatherdataws-1.0.0-SNAPSHOT.jar /tmp/bigdata/weatherdataws/app/
ENTRYPOINT ["/usr/bin/java", "-jar", "/tmp/bigdata/weatherdataws/app/weatherdataws-1.0.0-SNAPSHOT.jar", "&" ]
EXPOSE 8080
