package com.example.kafkaproducerapi.producer;

import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import vo.EmployeeVo;

@Configuration
@Slf4j
@AllArgsConstructor
public class KafkaProducer {

//	@Value(value = "${kafka.employee.topic")
	private final String employeeTopic = "employee-topic";

	private final KafkaTemplate<String, EmployeeVo> kafkaTemplate;

	public void produceEmployeeJson(String key, EmployeeVo employeeVo) {
		log.info("key :: {} | Message :: {} | topic :: {}", key, employeeVo, employeeTopic);
		var future = kafkaTemplate.send(employeeTopic, key, employeeVo);

		// approach 1
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

		// approach 2
//		future.whenComplete((result, ex) -> {
//		    if (ex == null) {
//		        log.info("Message sent successfully. Result :: {}",result);
//		    } else {
//		        log.error("Failed to send message :: {}",ex.getMessage());
//		    }
//		});

	}

}
