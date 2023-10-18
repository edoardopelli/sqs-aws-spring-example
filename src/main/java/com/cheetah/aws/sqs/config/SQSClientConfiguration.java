package com.cheetah.aws.sqs.config;

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

	@Bean
	AmazonSQS amazonSQSClient() {
		BasicAWSCredentials awsCredentials = new BasicAWSCredentials("ANUJDEKAVADIYAEXAMPLE",
				"2QvM4/Tdmf38SkcD/qalvXO4EXAMPLEKEY");
		return AmazonSQSClientBuilder.standard().withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
				.withEndpointConfiguration(new EndpointConfiguration("http://localhost:4566", "eu-central-1")).build();
	}

}
