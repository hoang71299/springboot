package com.baitap.demo.controller;


import com.baitap.demo.Employee;

import com.baitap.demo.dto.ApiResponse;
import com.baitap.demo.dto.JsonInclude;
import com.baitap.demo.enums.Gender;
import com.baitap.demo.exception.ApiException;
import com.baitap.demo.exception.ErrorCode;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
	private final List<Employee> employees = new ArrayList<>(
			Arrays.asList(
					new Employee(UUID.randomUUID(), "Nguyen Van A",
							LocalDate.of(2003, 1, 1), Gender.MALE, 3000000.0, "0123456789", 1),
					new Employee(UUID.randomUUID(), "Nguyen Van B",
							LocalDate.of(2023, 12, 1), Gender.MALE, 8000000.0, "0123456789", 2),
					new Employee(UUID.randomUUID(), "Nguyen Van C",
							LocalDate.of(2004, 1, 1), Gender.FEMALE, 15000000.0, "0123456789", 1)

			)
	);


	@GetMapping
	public ResponseEntity<?> getAll(
			@RequestParam(required = false) String name,
			@RequestParam(required = false)  LocalDate dobFrom,
			@RequestParam(required = false) LocalDate dobTo,
			@RequestParam(required = false) Gender gender,
			@RequestParam(required = false) String salaryRange,
			@RequestParam(required = false) String phone,
			@RequestParam(required = false) Integer departmentId

	) {

		List<Employee> filteredEmployees = employees.stream()
				.filter(e -> (name == null || e.getName().toLowerCase().contains(name.toLowerCase())))
				.filter(e -> (dobFrom == null || !e.getDateOfBirth().isBefore(dobFrom)))
				.filter(e -> (dobTo == null || !e.getDateOfBirth().isAfter(dobTo)))
				.filter(e -> (gender == null || e.getGender() == gender))
				.filter(e -> (phone == null || e.getPhone().contains(phone)))
				.filter(e -> (departmentId == null || Objects.equals(e.getDepartmentId(), departmentId)))
				.filter(e -> {
					if (salaryRange == null) {
						return true;
					}

					return switch (salaryRange) {
						case "lt5" -> 	e.getSalary() < 5000000;
						case "5-10" -> e.getSalary() >= 5000000 && e.getSalary() < 10000000;
						case "10-20" -> e.getSalary() >= 10000000 && e.getSalary() < 20000000;
						case "gt20" -> e.getSalary() >= 20000000;
						default -> true;
					};
				})
				.toList();


		return JsonInclude.ok(filteredEmployees);
	}

	@PostMapping
	public ResponseEntity<ApiResponse<Employee>> createEmployee(@RequestBody Employee employee) {
		employee.setId(UUID.randomUUID());
		employees.add(employee);
		return JsonInclude.created(employee);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable UUID id) {
		for (Employee employee : employees) {
			if (employee.getId().equals(id)) {
				return ResponseEntity.ok(employee);
			}
		}
		throw  new ApiException(ErrorCode.EMPLOYEE_NOT_FOUND);
	}

	@PutMapping("/{id}")
	public  ResponseEntity<ApiResponse<Employee>> updateEmployee(@PathVariable UUID id, @RequestBody Employee employee) {

		for (int i = 0; i < employees.size(); i++) {
			if (employees.get(i).getId().equals(id)) {
				employee.setId(id);
				employees.set(i, employee);
				return JsonInclude.ok(employees.get(i));
			}
		}
		throw  new ApiException(ErrorCode.EMPLOYEE_NOT_FOUND);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteEmployee(@PathVariable UUID id) {
		for(Employee employee : employees){
			if(employee.getId().equals(id)){
				employees.remove(employee);
				return JsonInclude.noContent();
			}
		}
		throw  new ApiException(ErrorCode.EMPLOYEE_NOT_FOUND);
	}

}
