package com.baitap.demo.modal;

import com.baitap.demo.enums.Gender;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
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
@Entity
public class Employee {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	String name;
	LocalDate dob;
	@Column(columnDefinition = "enum('MALE', 'FEMALE', 'OTHER')")
	@Enumerated(EnumType.STRING)
	Gender gender ;
	BigDecimal salary;
	String phone;
	@ManyToOne
	@JsonIgnoreProperties("employees")
	Department department;
	@CreationTimestamp
	@Column(updatable = false)
	LocalDateTime createdAt;
	@UpdateTimestamp
	LocalDateTime updatedAt;
}
