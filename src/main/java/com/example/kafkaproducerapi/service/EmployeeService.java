package com.example.kafkaproducerapi.service;

import vo.EmployeeVo;

public interface EmployeeService {

	public String produceEmployeeJson(EmployeeVo employeeVo);
}
