package com.baitap.demo.dto.employee;

import com.baitap.demo.dto.Department.DepartmentRequest;
import com.baitap.demo.enums.Gender;
import com.baitap.demo.modal.Department;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class EmployeeRequest {
	String name;
	LocalDate dob;
	Gender gender ;
	BigDecimal salary;
	String phone;
	DepartmentRequest department;
}
