package com.baitap.demo.service;

import com.baitap.demo.dto.employee.EmployeeSearchRequest;
import com.baitap.demo.modal.Employee;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;


public interface IEmployeeService {
	List<Employee> findByAttributes(EmployeeSearchRequest employeeSearchRequest);
	Employee findById(UUID id);
	Employee save(Employee employee);

	void delete(UUID id);
}
