package com.baitap.demo.repository.impl;

import com.baitap.demo.dto.employee.EmployeeSearchRequest;
import com.baitap.demo.enums.Gender;
import com.baitap.demo.modal.Employee;
import com.baitap.demo.repository.IEmployeeRepository;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import javax.management.Query;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.*;

@Repository
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class EmployeeRepository implements IEmployeeRepository {


	@Override
	public List<Employee> findByAttributes(EmployeeSearchRequest employeeSearchRequest) {

		Session session = ConnectionUtil.sessionFactory.openSession();

		String hql = "from Employee e join e.department d where"+
				"(:name is null or e.name like concat('%',:name,'%')) and"+
				"(:dobFrom is null or e.dob >= :dobFrom) and"+
				"(:dobTo is null or e.dob <= :dobTo) and"+
				"(:gender is null or e.gender = :gender) and"+

				"(:phone is null or e.phone like concat('%',:phone,'%')) and"+
				"(:departmentId is null or e.department.id = :departmentId)";

			if(employeeSearchRequest.getSalaryRange() != null){
				hql += "and(";
				switch (employeeSearchRequest.getSalaryRange()){
					case "lt5":
						hql += "e.salary < 5000";
						break;
					case "5-10":
						hql += "e.salary >= 50000 and e.salary <= 10000";
						break;
					case "10-20":
						hql += "e.salary >= 10000 and e.salary <= 20000";
						break;
					case "gt20":
						hql += "e.salary > 20000";
						break;

				}
				hql += ")";

			}


		List <Employee> employees = session.createQuery(hql, Employee.class)
				.setParameter("name", employeeSearchRequest.getName())
				.setParameter("dobFrom", employeeSearchRequest.getDobFrom())
				.setParameter("dobTo", employeeSearchRequest.getDobTo())
				.setParameter("gender", employeeSearchRequest.getGender())
				.setParameter("phone", employeeSearchRequest.getPhone())
				.setParameter("departmentId", employeeSearchRequest.getDepartmentId())
				.getResultList();
			return employees;

	}

	@Override
	public Employee findById(int id) {
		String hql = "from Employee e where e.id = :id";
		Session session = ConnectionUtil.sessionFactory.openSession();
		Employee employee = session.createQuery(hql, Employee.class)
				.setParameter("id", id)
				.getSingleResult();
		return employee;
	}

	@Override
	public Employee save(Employee employee) {
		try (Session session = ConnectionUtil.sessionFactory.openSession()) {
			Transaction transaction = session.beginTransaction();

			try {

				session.saveOrUpdate(employee);

				transaction.commit();
			} catch (Exception e) {
				if (transaction != null) {
					transaction.rollback(); // Rollback nếu có lỗi
				}
				throw new RuntimeException(e);
			}
		}
		return employee;
	}

	@Override
	public void delete(int id) {
		Session session = ConnectionUtil.sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		session.createQuery("DELETE FROM Employee e WHERE e.id = :id")
				.setParameter("id", id)
				.executeUpdate();
		transaction.commit();
	}

}
