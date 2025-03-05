package com.baitap.demo.controller;


import com.baitap.demo.modal.Department;
import com.baitap.demo.dto.ApiResponse;
import com.baitap.demo.service.IDepartmentService;
import com.baitap.demo.util.JsonInclude;
import com.baitap.demo.exception.ApiException;
import com.baitap.demo.exception.ErrorCode;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/departments")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class DepartmentController {
	IDepartmentService departmentService;

	@GetMapping
	public ResponseEntity<?> getDepartments(){
		return JsonInclude.ok(departmentService.findAll());
	}
	@GetMapping("/{id}")
	public ResponseEntity<ApiResponse<Department>> getDepartmentById(@PathVariable int id){
		if (departmentService.findById(id) != null){
			return JsonInclude.ok(departmentService.findById(id));
		}
		throw new ApiException(ErrorCode.DEPARTMENT_NOT_FOUND);
	}
	@PostMapping
	public  ResponseEntity<ApiResponse<Department>> createDepartment(@RequestBody Department department){
		return JsonInclude.created(departmentService.save(department));
	}
	@PutMapping("/{id}")
	public ResponseEntity<ApiResponse<Department>> updateDepartment(@PathVariable long id ,
																	@RequestBody Department department){

		if(departmentService.findById(id) == null){
			throw new ApiException(ErrorCode.DEPARTMENT_NOT_FOUND);
		}
		department.setId(id);
		return JsonInclude.ok(departmentService.save(department));

	}
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteDepartment(@PathVariable long id){
		if(departmentService.findById(id) != null){
			departmentService.delete(id);
			return JsonInclude.noContent();
		}
		throw new ApiException(ErrorCode.DEPARTMENT_NOT_FOUND);
	}

}
