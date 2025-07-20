package com.example.kafkaproducerapi.kafka;


import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;

import lombok.extern.slf4j.Slf4j;
import vo.EmployeeVo;

@Configuration
@Slf4j
public class KafkaProducer {

//	@Value("${kafka.topic")
	private final String kafkaTopic = "mytopic";

	@Autowired
	private KafkaTemplate<String, EmployeeVo> kafkaTemplate;

	public void produceJson(String key, EmployeeVo employeeVo) {
		log.info("key :: {} | Message :: {} | topic :: {}", key, employeeVo, kafkaTopic);
		CompletableFuture<SendResult<String, EmployeeVo>> future = kafkaTemplate.send(kafkaTopic, key, employeeVo);
		
		//approach 1
		future.thenAccept(result -> {
			log.info("Message sent successfully. Result :: {}", result);
			// You can access the result here, for example:
			// String topic = result.getRecordMetadata().topic();
			// int partition = result.getRecordMetadata().partition();
			// long offset = result.getRecordMetadata().offset();
			// System.out.println("Topic: " + topic + ", Partition: " + partition + ",
			// Offset: " + offset);
		}).exceptionally(ex -> {
			log.error("Failed to send message :: {} ", ex.getMessage());
			return null; // Or handle the exception as needed
		});
		
		//approach 2
//		future.whenComplete((result, ex) -> {
//		    if (ex == null) {
//		        log.info("Message sent successfully. Result :: {}",result);
//		    } else {
//		        log.error("Failed to send message :: {}",ex.getMessage());
//		    }
//		});
		
	}

}
