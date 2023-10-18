package com.cheetah.aws.sqs.producers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.model.GetQueueUrlRequest;
import com.amazonaws.services.sqs.model.GetQueueUrlResult;
import com.amazonaws.services.sqs.model.InvalidMessageContentsException;
import com.amazonaws.services.sqs.model.QueueDoesNotExistException;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class SQSProducer {
	
	@Autowired
	private AmazonSQS amazonSQSClient;
	
	@Value("${aws.sqs.queue.name}")
	private String awsQueueName;

	public void publishExpense(String message) {        
	    try {            
	        GetQueueUrlResult queueUrl = amazonSQSClient.getQueueUrl(awsQueueName);            
	        log.info("Sending message: {}", message);            
	        amazonSQSClient.sendMessage(queueUrl.getQueueUrl(), message);        
	    } catch (QueueDoesNotExistException | InvalidMessageContentsException e) {           
	         log.error("Queue does not exist {}", e.getMessage());        
	        }    
	}

}
