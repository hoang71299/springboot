package com.baitap.demo.service;

import com.baitap.demo.modal.Department;

import java.util.List;

public interface IDepartmentService {
	List<Department> findAll();

	Department findById(int id);

	Department save(Department department);

	void delete(int id);
}
