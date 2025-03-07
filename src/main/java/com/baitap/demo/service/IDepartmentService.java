package com.baitap.demo.service;

import com.baitap.demo.modal.Department;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IDepartmentService {
	Page<Department> findAll(Pageable pageable);

	Department findById(long id);

	Department save(Department department);

	void delete(long id);
}
