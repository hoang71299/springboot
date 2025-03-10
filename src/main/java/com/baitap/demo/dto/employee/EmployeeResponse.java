package com.baitap.demo.dto.employee;

import com.baitap.demo.dto.Department.DepartmentResponse;
import com.baitap.demo.enums.Gender;
import com.baitap.demo.modal.Department;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class EmployeeResponse {
	int id;
	String name;
	LocalDate dob;
	Gender gender ;
	BigDecimal salary;
	String phone;
	DepartmentResponse department;
}
