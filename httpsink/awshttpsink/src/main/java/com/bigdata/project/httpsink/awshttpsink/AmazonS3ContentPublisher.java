package com.bigdata.project.httpsink.awshttpsink;

import java.io.ByteArrayInputStream;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;

@Component
@PropertySource("classpath:application.properties")
public class AmazonS3ContentPublisher {

	@Value("${aws.s3.bucket}")
	private String bucketName;
	@Value("${aws.s3.region}")
	private String region;
	@Value("${aws.s3.accessKey}")
	private String accessKey;
	@Value("${aws.s3.secretKey}")
	private String secretKey;
	private AmazonS3 s3;
	
	public AmazonS3ContentPublisher() {
		
	}

	public void publishMessage(String content) {
		if(s3 == null) {
			bootstrapClient();
		}
		try {
			ObjectMetadata objectMetadata = new ObjectMetadata();
			PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName,
					"weatherdata/content-" + System.currentTimeMillis() + ".txt",
					new ByteArrayInputStream(content.getBytes()), objectMetadata);
			s3.putObject(putObjectRequest);
		} catch (AmazonServiceException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void bootstrapClient() {
		System.out.println("Key: "+ accessKey + " | Secret: "+secretKey);
		BasicAWSCredentials awsCredentials = new BasicAWSCredentials(accessKey, secretKey);
		s3 = AmazonS3ClientBuilder.standard()
				.withCredentials(new AWSStaticCredentialsProvider(awsCredentials)).withRegion(region).build();
		Bucket bucket = s3.listBuckets().stream().filter(bkt -> bkt.getName().equals(bucketName)).findAny()
				.orElse(null);
		if (bucket == null) {
			s3.createBucket(bucketName);
		}
	}

}
