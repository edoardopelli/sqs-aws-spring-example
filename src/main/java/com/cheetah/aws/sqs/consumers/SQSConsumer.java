package com.cheetah.aws.sqs.consumers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.model.Message;
import com.amazonaws.services.sqs.model.QueueDoesNotExistException;
import com.amazonaws.services.sqs.model.ReceiveMessageResult;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class SQSConsumer {
	
	@Autowired
	private AmazonSQS amazonSQSClient;

	@Scheduled(fixedDelay = 5000) // executes on every 5 second gap.
    public void receiveMessages() {
        try {
            String queueUrl = amazonSQSClient.getQueueUrl("sqs_prova_1").getQueueUrl();
            ReceiveMessageResult receiveMessageResult = amazonSQSClient.receiveMessage(queueUrl);
            if (!receiveMessageResult.getMessages().isEmpty()) {
                Message message = receiveMessageResult.getMessages().get(0);
                log.info("Message Id: {} - Body: {}",message.getMessageId(), message.getBody());
                amazonSQSClient.deleteMessage(queueUrl, message.getReceiptHandle());
            }
        } catch (QueueDoesNotExistException e) {
            log.error("Queue does not exist {}", e.getMessage());
        }
    }

}
