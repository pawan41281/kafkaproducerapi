package com.example.kafkaproducerapi.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.example.kafkaproducerapi.kafka.KafkaProducer;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import vo.EmployeeVo;

@Service
@Slf4j
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService{

	private final KafkaProducer kafkaProducer;
	
//	@Override
//	public String saveString(EmployeeVo employeeVo) {
//		log.info("employeeVo :: {}", employeeVo);
//		String referenceNumber = null;
//		referenceNumber = UUID.randomUUID().toString().replace("-", "").substring(0, 12).toUpperCase();
//		kafkaProducer.produceString(referenceNumber, employeeVo.toString());
//		log.info("Reference Number for employeeVo :: {}",referenceNumber);
//		return referenceNumber;
//	}
	
	@Override
	public String saveJson(EmployeeVo employeeVo) {
		log.info("employeeVo :: {}", employeeVo);
		String referenceNumber = null;
		referenceNumber = UUID.randomUUID().toString().replace("-", "").substring(0, 12).toUpperCase();
		kafkaProducer.produceJson(referenceNumber, employeeVo);
		log.info("Reference Number for employeeVo :: {}",referenceNumber);
		return referenceNumber;
	}
}
