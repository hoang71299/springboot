package com.baitap.demo.repository.impl;

import com.baitap.demo.modal.Department;
import com.baitap.demo.modal.Employee;
import com.baitap.demo.repository.IDepartmentRepository;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Repository
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class DepartmentRepository implements IDepartmentRepository {
	List<Department> departments = new ArrayList<>(
			Arrays.asList(
					new Department(1, "IT"),
					new Department(2, "HR"),
					new Department(3, "Sales")
			)
	);

	@Override
	public List<Department> findAll() {
		return departments;
	}

	@Override
	public Department findById(int id) {
		for (Department department : departments) {
			if (department.getId() == id) {
				return department;
			}
		}
		return null;
	}

	@Override
	public Department save(Department department) {
		for (int i = 0; i < departments.size(); i++) {
			if (departments.get(i).getId() == department.getId()){
				department.setId(departments.get(i).getId());
				departments.set(i, department);
				return department;
			}
			}

			department.setId(departments.size() + 1);
			departments.add(department);
			return department;
		}

	@Override
	public void delete(int id) {
		departments.removeIf(department -> department.getId() == id);
	}
}




