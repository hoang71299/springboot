package com.baitap.demo.repository;

import com.baitap.demo.dto.employee.EmployeeSearchRequest;
import com.baitap.demo.enums.Gender;
import com.baitap.demo.modal.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface IEmployeeRepository extends JpaRepository<Employee, Integer> {


	@Query(	value = """
			select e.* from Employee e  left join Department d on e.department_id = d.id
			where (:name is null or e.name like concat('%', :name, '%'))
			and (:dobFrom is null or e.dob >= :dobFrom)
			and (:dobTo is null or e.dob <= :dobTo)
			and (:gender is null or e.gender = :gender)
			and (:phone is null or e.phone like concat('%', :phone, '%'))
			and (:departmentId is null or e.department_id = :departmentId)
			and(
				case
					when :salaryRange = "lt5" then e.salary < 5000
					when :salaryRange = "5-10" then e.salary between 5000 and 10000
					when :salaryRange = "10-20" then e.salary between 10000 and 20000
					when :salaryRange = "gt20" then e.salary > 20000
				else true
				end
			)
			""",nativeQuery = true)
	List<Employee> findByAttributes(@Param("name") String name,
									@Param("dobFrom") LocalDate dobFrom,
									@Param("dobTo") LocalDate dobTo,
									@Param("gender") String gender,
									@Param("phone") String phone,
									@Param("departmentId") Integer departmentId,
									@Param("salaryRange") String salaryRange);
	Employee findById(long id);
	Employee save(Employee employee);

	void deleteById(long id);
}
