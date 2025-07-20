package com.example.kafkaproducerapi.controller;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.kafkaproducerapi.service.EmployeeServiceImpl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import vo.EmployeeVo;

@RestController
@RequestMapping("/employees")
@AllArgsConstructor
@Slf4j
public class EmployeeController {

	private final EmployeeServiceImpl employeeServiceImpl;

	@PostMapping("/json")
	public String saveJson(@Validated @RequestBody EmployeeVo employeeVo) {
		log.info("Request received to save the employeeVo :: {}", employeeVo);
		return employeeServiceImpl.produceEmployeeJson(employeeVo);
	}

}
