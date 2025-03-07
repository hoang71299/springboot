package com.baitap.demo.service.impl;

import com.baitap.demo.modal.Department;
import com.baitap.demo.repository.IDepartmentRepository;
import com.baitap.demo.repository.IEmployeeRepository;
import com.baitap.demo.service.IDepartmentService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class DepartmentService implements IDepartmentService {
	IDepartmentRepository departmentRepository;


	@Override
	public Page<Department> findAll(Pageable pageable) {
		return departmentRepository.findAll(pageable);
	}



	@Override
	public Department findById(long id) {
		return departmentRepository.findById(id);
	}


	@Override
	public Department save(Department department) {
		return departmentRepository.save(department);
	}

	@Override
	public void delete(long id) {
		departmentRepository.deleteById(id);
	}
}
