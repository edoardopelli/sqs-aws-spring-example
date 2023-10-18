package com.cheetah.aws.sqs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAutoConfiguration
public class SqsConsumerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SqsConsumerApplication.class, args);
	}

}
