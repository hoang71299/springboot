package com.baitap.demo.repository.impl;

import com.baitap.demo.dto.employee.EmployeeSearchRequest;
import com.baitap.demo.enums.Gender;
import com.baitap.demo.modal.Employee;
import com.baitap.demo.repository.IEmployeeRepository;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.*;

@Repository
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class EmployeeRepository implements IEmployeeRepository {

	List<Employee> employees = new ArrayList<>(
			Arrays.asList(
					new Employee(UUID.randomUUID(), "Nguyen Van A",
							LocalDate.of(2003, 1, 1), Gender.MALE, 3000000.0, "0123456789", 1),
					new Employee(UUID.randomUUID(), "Nguyen Van B",
							LocalDate.of(2023, 12, 1), Gender.MALE, 8000000.0, "0123456789", 2),
					new Employee(UUID.randomUUID(), "Nguyen Van C",
							LocalDate.of(2004, 1, 1), Gender.FEMALE, 15000000.0, "0123456789", 1)

			)
	);

	@Override
	public List<Employee> findByAttributes(EmployeeSearchRequest employeeSearchRequest) {
		return employees.stream()
				.filter(e -> (employeeSearchRequest.getName() == null
						|| e.getName().toLowerCase().contains(employeeSearchRequest.getName().toLowerCase())))
				.filter(e -> (employeeSearchRequest.getDobFrom() == null
						|| !e.getDateOfBirth().isBefore(employeeSearchRequest.getDobFrom())))
				.filter(e -> (employeeSearchRequest.getDobTo() == null
						|| !e.getDateOfBirth().isAfter(employeeSearchRequest.getDobTo())))
				.filter(e -> (employeeSearchRequest.getGender() == null
						|| e.getGender() == employeeSearchRequest.getGender()))
				.filter(e -> (employeeSearchRequest.getPhone() == null
						|| e.getPhone().contains(employeeSearchRequest.getPhone())))
				.filter(e -> (employeeSearchRequest.getDepartmentId() == null
						|| Objects.equals(e.getDepartmentId(), employeeSearchRequest.getDepartmentId())))
				.filter(e -> {
					if (employeeSearchRequest.getSalaryRange() == null) {
						return true;
					}

					return switch (employeeSearchRequest.getSalaryRange()) {
						case "lt5" -> e.getSalary() < 5000000;
						case "5-10" -> e.getSalary() >= 5000000 && e.getSalary() < 10000000;
						case "10-20" -> e.getSalary() >= 10000000 && e.getSalary() < 20000000;
						case "gt20" -> e.getSalary() >= 20000000;
						default -> true;
					};
				})
				.toList();
	}

	@Override
	public Employee findById(UUID id) {
		for (Employee employee : employees) {
			if (employee.getId().equals(id)) {
				return employee;
			}
		}
		return null;
	}

	@Override
	public Employee save(Employee employee) {
		for (int i = 0; i < employees.size(); i++) {
			if (employees.get(i).getId().equals(employee.getId())) {
				employee.setId(employees.get(i).getId());
				employees.set(i, employee);
				return employee;
			}
		}

		employee.setId(UUID.randomUUID());
		employees.add(employee);
		return employee;
	}


	@Override
	public void delete(UUID id) {
		employees.removeIf(employee -> employee.getId().equals(id));
	}
}
