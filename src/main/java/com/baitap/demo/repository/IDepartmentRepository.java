package com.baitap.demo.repository;

import com.baitap.demo.dto.ApiResponse;
import com.baitap.demo.exception.ApiException;
import com.baitap.demo.exception.ErrorCode;
import com.baitap.demo.modal.Department;
import com.baitap.demo.util.JsonInclude;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface IDepartmentRepository extends JpaRepository<Department, Long> {
	List<Department> findAll();

	Department findById(long id);

	Department save(Department department);

	void deleteById(int id);

}
