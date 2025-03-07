package com.baitap.demo.service.impl;

import com.baitap.demo.dto.employee.EmployeeSearchRequest;
import com.baitap.demo.enums.Gender;
import com.baitap.demo.modal.Employee;
import com.baitap.demo.repository.IEmployeeRepository;
import com.baitap.demo.service.IEmployeeService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class EmployeeService implements IEmployeeService {
	IEmployeeRepository employeeRepository;


	@Override
	public Page<Employee> findByAttributes(EmployeeSearchRequest employeeSearchRequest, Pageable pageable) {
		return employeeRepository.findByAttributes(employeeSearchRequest.getName(),
				employeeSearchRequest.getDobFrom(),
				employeeSearchRequest.getDobTo(),
				employeeSearchRequest.getGender() == null ? null : employeeSearchRequest.getGender().toString(),
				employeeSearchRequest.getPhone(),
				employeeSearchRequest.getDepartmentId(),
				employeeSearchRequest.getSalaryRange(),
				pageable
				);
	};

	@Override
	public Employee findById(int id) {
		return employeeRepository.findById(id);
	}

	@Override
	public Employee save(Employee employee) {
		return employeeRepository.save(employee);
	}

	@Override
	public void delete(int id) {
		employeeRepository.deleteById(id);
	}
}
