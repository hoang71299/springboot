package com.baitap.demo.dto.employee;


import com.baitap.demo.enums.Gender;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EmployeeSearchRequest {

	String name;
	LocalDate dobFrom;
	LocalDate dobTo;
	Gender gender;
	String salaryRange;
	String phone;
	Integer departmentId;
}
