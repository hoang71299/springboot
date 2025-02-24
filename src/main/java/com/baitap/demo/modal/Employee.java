package com.baitap.demo.modal;

import com.baitap.demo.enums.Gender;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class Employee {
	int id;
	String name;
	LocalDate dateOfBirth;
	Gender gender;
	Double salary;
	String phone;
	Integer departmentId;


}
