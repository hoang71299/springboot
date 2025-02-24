package com.baitap.demo.repository;

import com.baitap.demo.dto.ApiResponse;
import com.baitap.demo.exception.ApiException;
import com.baitap.demo.exception.ErrorCode;
import com.baitap.demo.modal.Department;
import com.baitap.demo.util.JsonInclude;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface IDepartmentRepository {
	List<Department> findAll();

	Department findById(int id);

	Department save(Department department);

	void delete(int id);

}
