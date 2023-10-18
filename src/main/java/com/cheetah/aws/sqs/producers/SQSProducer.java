package com.cheetah.aws.sqs.producers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.model.GetQueueUrlResult;
import com.amazonaws.services.sqs.model.InvalidMessageContentsException;
import com.amazonaws.services.sqs.model.QueueDoesNotExistException;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class SQSProducer {
	
	@Autowired
	private AmazonSQS amazonSQSClient;

	public void publishExpense(String message) {        
	    try {            
	        GetQueueUrlResult queueUrl = amazonSQSClient.getQueueUrl("sqs_prova_1");            
	        log.info("Sending message: {}", message);            
	        amazonSQSClient.sendMessage(queueUrl.getQueueUrl(), message);        
	    } catch (QueueDoesNotExistException | InvalidMessageContentsException e) {           
	         log.error("Queue does not exist {}", e.getMessage());        
	        }    
	}

}
