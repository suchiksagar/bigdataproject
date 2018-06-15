#!/bin/bash

java -jar /tmp/bigdata/filepollws/app/filepollws-1.0.0-SNAPSHOT.jar &
java -jar /tmp/bigdata/weatherdataws/app/weatherdataws-1.0.0-SNAPSHOT.jar --APIKEY=$APIKEY &
while :
do
    sleep 120 && find /tmp/bigdata/weatherdata/dump -mmin +10 -type f -exec rm -fv {} \; > rmfiles.log
done