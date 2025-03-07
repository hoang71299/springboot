package com.baitap.demo.service;

import com.baitap.demo.dto.employee.EmployeeSearchRequest;
import com.baitap.demo.modal.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;


public interface IEmployeeService {
	Page<Employee> findByAttributes(EmployeeSearchRequest employeeSearchRequest, Pageable pageable);
	Employee findById(int id);
	Employee save(Employee employee);

	void delete(int id);
}
