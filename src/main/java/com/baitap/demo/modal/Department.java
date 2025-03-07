package com.baitap.demo.modal;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@Entity
public class Department {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	String name;

	@OneToMany(mappedBy = "department")
	private List<Employee> employees;

	@CreationTimestamp
	@Column(updatable = false)
	LocalDateTime createdAt;
	@UpdateTimestamp
	LocalDateTime updatedAt;
}
