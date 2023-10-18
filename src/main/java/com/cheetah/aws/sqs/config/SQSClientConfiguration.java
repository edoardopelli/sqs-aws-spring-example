package com.cheetah.aws.sqs.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder.EndpointConfiguration;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;

@Configuration
@EnableScheduling
public class SQSClientConfiguration {

	@Value("${aws.sqs.endpoint}")
	private String awsEndpoint;
	
	@Value("${aws.region}")
	private String awsRegion;

	@Value("${aws.secretKey}")
	private String awsSecretKey;

	@Value("${aws.accessKey}")
	private String awsAccessKey;
	
	

	@Bean
	AmazonSQS amazonSQSClient() {
		BasicAWSCredentials awsCredentials = new BasicAWSCredentials(awsAccessKey,
				awsSecretKey);
		return AmazonSQSClientBuilder.standard().withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
				.withEndpointConfiguration(new EndpointConfiguration(awsEndpoint, awsRegion)).build();
	}

}
