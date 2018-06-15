#!/bin/sh
sed -i "s/HOST_VALUE/$DOCKER_HOST/g" /opt/flume-config/flume.conf
cd /opt/flume/bin
start-flume > /tmp/flumelog.txt 2>&1 &
while :
do
	TMS=$(date +%s)
	curl http://$SERVICE_HOST:8090/filepollws/v1/filepoll > /tmp/dump/data-dump-$TMS.json
	curl -X POST -H "Content-Type: text/plain" --data "$(</tmp/dump/data-dump-$TMS.json)" http://$SINK_HOST:7075/httpsink/v1/publish
	sleep 12
	mv /tmp/dump/data-dump-$TMS.json /tmp/flumefeed/data-dump-$TMS.json
done
