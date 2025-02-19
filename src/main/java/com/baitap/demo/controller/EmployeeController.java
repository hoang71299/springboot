package com.baitap.demo.controller;


import com.baitap.demo.Employee;

import com.baitap.demo.enums.Gender;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
	private final List<Employee> employees = new ArrayList<>(
			Arrays.asList(
					new Employee(UUID.randomUUID(), "Nguyen Van A", LocalDate.of(1990, 1, 1),
							Gender.MALE, 5000000.0, "0123456789"),
					new Employee(UUID.randomUUID(), "Nguyen Van B", LocalDate.of(2000, 7, 1),
							Gender.FEMALE, 5000000.0, "0123456789"),
					new Employee(UUID.randomUUID(), "Nguyen Van C", LocalDate.of(2003, 12, 1),
							Gender.FEMALE, 5000000.0, "0123456789")
			)
	);


	@GetMapping
	public ResponseEntity<List<Employee>> getEmployees() {
		return ResponseEntity.ok(employees);
	}

	@PostMapping
	public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) {
		employee.setId(UUID.randomUUID());
		employees.add(employee);
		return ResponseEntity.status(HttpStatus.CREATED).body(employee);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable UUID id) {
		for (Employee employee : employees) {
			if (employee.getId().equals(id)) {
				return ResponseEntity.ok(employee);
			}
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}

	@PutMapping("/{id}")
	public  ResponseEntity<Employee> updateEmployee(@PathVariable UUID id, @RequestBody Employee employee) {

		for (int i = 0; i < employees.size(); i++) {
			if (employees.get(i).getId().equals(id)) {
				employees.set(i, employee);
				return ResponseEntity.ok(employee);
			}
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteEmployee(@PathVariable UUID id) {
		for(Employee employee : employees){
			if(employee.getId().equals(id)){
				employees.remove(employee);
				return ResponseEntity.noContent().build();
			}
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}

}
