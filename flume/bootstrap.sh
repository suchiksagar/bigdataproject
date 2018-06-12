#!/bin/sh
sed -i "s/HOST_VALUE/$DOCKER_HOST/g" /opt/flume-config/flume.conf
cd /opt/flume/bin
start-flume > /tmp/flumelog.txt 2>&1 &
while :
do
	TMS=$(date +%s)
	curl http://$SERVICE_HOST:8090/filepollws/v1/filepoll > /tmp/dump/data-dump-$TMS.json
	sleep 12
	mv /tmp/dump/data-dump-$TMS.json /tmp/flumefeed/data-dump-$TMS.json
done
