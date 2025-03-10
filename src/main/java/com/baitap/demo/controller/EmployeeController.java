package com.baitap.demo.controller;


import com.baitap.demo.dto.employee.EmployeeRequest;
import com.baitap.demo.dto.employee.EmployeeResponse;
import com.baitap.demo.dto.employee.EmployeeSearchRequest;
import com.baitap.demo.dto.page.PageResponse;
import com.baitap.demo.mapper.IEmployeeMapper;
import com.baitap.demo.modal.Employee;

import com.baitap.demo.dto.ApiResponse;
import com.baitap.demo.service.IEmployeeService;
import com.baitap.demo.util.JsonInclude;
import com.baitap.demo.enums.Gender;
import com.baitap.demo.exception.ApiException;
import com.baitap.demo.exception.ErrorCode;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.*;

@RestController
@RequestMapping("/employees")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class EmployeeController {
	IEmployeeService employeeService;
	IEmployeeMapper employeeMapper;
	@GetMapping
	public ResponseEntity<?> getAll(
			EmployeeSearchRequest employeeSearchRequest,
			Pageable pageable
	) {

		return JsonInclude.ok(new PageResponse<>(employeeService.findByAttributes(employeeSearchRequest,pageable)
				.map(employeeMapper::employeeToEmployeeResponse)
		));
	}

	@PostMapping
	public ResponseEntity<ApiResponse<EmployeeResponse>> createEmployee(@RequestBody EmployeeRequest employeeRequest) {
		Employee employee = employeeMapper.employeeRequestToEmployee(employeeRequest);
		employee = employeeService.save(employee);
		EmployeeResponse employeeResponse = employeeMapper.employeeToEmployeeResponse(employee);
		return JsonInclude.created(employeeResponse);
	}

	@GetMapping("/{id}")
	public ResponseEntity<ApiResponse<EmployeeResponse>> getEmployeeById(@PathVariable int id) {
		if(employeeService.findById(id) != null){
			EmployeeResponse employeeResponse = employeeMapper.employeeToEmployeeResponse(employeeService.findById(id));
			return JsonInclude.ok(employeeResponse);
		}
		throw  new ApiException(ErrorCode.EMPLOYEE_NOT_FOUND);
	}

	@PutMapping("/{id}")
	public ResponseEntity<ApiResponse<EmployeeResponse>> updateEmployee(@PathVariable int id, @RequestBody EmployeeRequest employeeRequest) {
		Employee existingEmployee = employeeService.findById(id);
		if (existingEmployee != null) {
			Employee employee = employeeMapper.employeeRequestToEmployee(employeeRequest);
			employee.setId(id);
			employee = employeeService.save(employee);
			EmployeeResponse employeeResponse = employeeMapper.employeeToEmployeeResponse(employee);
			return JsonInclude.ok(employeeResponse);
		}
		throw new ApiException(ErrorCode.EMPLOYEE_NOT_FOUND);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteEmployee(@PathVariable int id) {
		if(employeeService.findById(id) == null){
			throw new ApiException(ErrorCode.EMPLOYEE_NOT_FOUND);
		}
		employeeService.delete(id);
		return  JsonInclude.noContent();
	}

}
