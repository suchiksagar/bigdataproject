#!/bin/sh

java -jar /tmp/bigdata/filepollws/app/filepollws-1.0.0-SNAPSHOT.jar &
java -jar /tmp/bigdata/weatherdataws/app/weatherdataws-1.0.0-SNAPSHOT.jar --APIKEY=$APIKEY