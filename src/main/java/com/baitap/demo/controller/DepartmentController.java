package com.baitap.demo.controller;


import com.baitap.demo.modal.Department;
import com.baitap.demo.dto.ApiResponse;
import com.baitap.demo.util.JsonInclude;
import com.baitap.demo.exception.ApiException;
import com.baitap.demo.exception.ErrorCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/departments")
public class DepartmentController {
	private final List<Department> departments =new ArrayList<>(
			Arrays.asList(
					new Department(1,"IT"),
					new Department(2,"HR"),
					new Department(3,"Sales")
			)
	);

	@GetMapping
	public ResponseEntity<ApiResponse<List<Department>>> getDepartments(){
		return JsonInclude.ok(departments);
	}
	@GetMapping("/{id}")
	public ResponseEntity<ApiResponse<Department>> getDepartmentById(@PathVariable int id){
		for(Department department : departments){
			if(department.getId() == id){
				return JsonInclude.ok(department);
			}
		}
		throw new ApiException(ErrorCode.DEPARTMENT_NOT_FOUND);
	}
	@PostMapping
	public  ResponseEntity<ApiResponse<Department>> createDepartment(@RequestBody Department department){
		department.setId(departments.size() + 1);
		departments.add(department);
		return JsonInclude.created(department);
	}
	@PutMapping("/{id}")
	public ResponseEntity<ApiResponse<Department>> updateDepartment(@PathVariable int id ,
																	@RequestBody Department department){
		for (int i = 0 ; i < departments.size();i++){
			if(departments.get(i).getId() == id){
				department.setId(id);
				departments.set(i,department);
				return JsonInclude.ok(department);
			}
		}
		throw new ApiException(ErrorCode.DEPARTMENT_NOT_FOUND);
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteDepartment(@PathVariable int id){
		for(Department department : departments){
			if(department.getId() == id){
				departments.remove(department);
				return JsonInclude.noContent();
			}
		}
		throw new ApiException(ErrorCode.DEPARTMENT_NOT_FOUND);
	}

}
