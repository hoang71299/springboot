package com.baitap.demo.service.repository;

import com.baitap.demo.dto.employee.EmployeeSearchRequest;
import com.baitap.demo.modal.Employee;

import java.util.List;
import java.util.UUID;

public interface IEmployeeRepository {

	List<Employee> findByAttributes(EmployeeSearchRequest employeeSearchRequest);
	Employee findById(UUID id);
	Employee save(Employee employee);

	void delete(UUID id);
}
