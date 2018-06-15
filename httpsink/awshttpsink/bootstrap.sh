#!/bin/bash
java -jar /tmp/downloads/sink/httpsink.jar --aws.s3.bucket=$AWS_S3_BUCKET --aws.s3.region=$AWS_REGION --aws.s3.accessKey=$AWS_ACCESSKEY --aws.s3.secretKey=$AWS_SECRETKEY