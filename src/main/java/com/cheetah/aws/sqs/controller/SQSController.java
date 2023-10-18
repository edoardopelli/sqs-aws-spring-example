package com.cheetah.aws.sqs.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.cheetah.aws.sqs.producers.SQSProducer;

@RestController
public class SQSController {

	@Autowired
	private SQSProducer producer;

	@GetMapping("/sendSimpleMessage")
	public ResponseEntity<String> simpleMessage() {
		String id = UUID.randomUUID().toString();
		producer.publishExpense("Hello world " + id);
		return ResponseEntity.ok(id);

	}

	@GetMapping("/sendMultiMessage/{total}")
	public ResponseEntity<List<String>> simpleMultiMessage(@PathVariable("total") Integer total) {
		List<String> ids = new ArrayList<>();
		for (int i = 0; i < total; i++) {
			String id = UUID.randomUUID().toString();
			producer.publishExpense("Hello world " + id);
			ids.add(id);
		}
		return ResponseEntity.ok(ids);

	}
}
