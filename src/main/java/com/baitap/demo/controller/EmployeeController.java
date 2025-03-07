package com.baitap.demo.controller;


import com.baitap.demo.dto.employee.EmployeeSearchRequest;
import com.baitap.demo.dto.page.PageResponse;
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
	@GetMapping
	public ResponseEntity<?> getAll(
			EmployeeSearchRequest employeeSearchRequest,
			Pageable pageable
	) {

		return JsonInclude.ok(new PageResponse<>(employeeService.findByAttributes(employeeSearchRequest,pageable)));
	}

	@PostMapping
	public ResponseEntity<ApiResponse<Employee>> createEmployee(@RequestBody Employee employee) {

		return JsonInclude.created(employeeService.save(employee));
	}

	@GetMapping("/{id}")
	public ResponseEntity<ApiResponse<Employee>> getEmployeeById(@PathVariable int id) {
		if(employeeService.findById(id) != null){
			return JsonInclude.ok(employeeService.findById(id));
		}
		throw  new ApiException(ErrorCode.EMPLOYEE_NOT_FOUND);
	}

	@PutMapping("/{id}")
	public ResponseEntity<ApiResponse<Employee>> updateEmployee(@PathVariable int id, @RequestBody Employee employee) {
		Employee existingEmployee = employeeService.findById(id);
		if (existingEmployee != null) {
			employee.setId(id);
			return JsonInclude.ok(employeeService.save(employee));
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
