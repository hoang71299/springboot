package com.baitap.demo.service;

import com.baitap.demo.modal.Department;

import java.util.List;

public interface IDepartmentService {
	List<Department> findAll();

	Department findById(long id);

	Department save(Department department);

	void delete(long id);
}
