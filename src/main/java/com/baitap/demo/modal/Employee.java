package com.baitap.demo.modal;

import com.baitap.demo.enums.Gender;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@Entity
public class Employee {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	String name;
	LocalDate dob;
	@Column(columnDefinition = "enum('MALE', 'FEMALE', 'OTHER')")
	@Enumerated(EnumType.STRING)
	Gender gender;
	BigDecimal salary;
	String phone;
	@ManyToOne
	Department department;


}
