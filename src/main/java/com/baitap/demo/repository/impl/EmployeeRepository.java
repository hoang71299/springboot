package com.baitap.demo.repository.impl;

import com.baitap.demo.dto.employee.EmployeeSearchRequest;
import com.baitap.demo.enums.Gender;
import com.baitap.demo.modal.Employee;
import com.baitap.demo.repository.IEmployeeRepository;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Repository;

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
		List<Employee> employees = new ArrayList<>();
		String sql = "select id , name , dob , gender , salary , phone , department_id from employee  where  1=1";

		List<Object> params = new ArrayList<>();
		if(employeeSearchRequest.getName() != null){
			sql += " and name like ?";
			params.add("%"+employeeSearchRequest.getName()+"%");
		}

		if(employeeSearchRequest.getDobFrom() != null){
			sql += " and dob >= ?";
			params.add(employeeSearchRequest.getDobFrom());
		}

		if(employeeSearchRequest.getDobTo() != null){
			sql += " and dob <= ?";
			params.add(employeeSearchRequest.getDobTo());
		}

		if(employeeSearchRequest.getGender() != null){
			sql += " and gender = ?";
			params.add(employeeSearchRequest.getGender());
		}

		if(employeeSearchRequest.getSalaryRange() != null){
			switch (employeeSearchRequest.getSalaryRange()){
				case "lt5":
					sql += " and salary < 5000";
					break;
				case "5-10":
					sql += " and salary >= 5000 and salary < 10000";
					break;
				case "10-20":
					sql += " and salary >= 10000 and salary < 20000";
					break;
				case "gt20":
					sql += " and salary >= 20000";
					break;
			}
		}

		if(employeeSearchRequest.getPhone() != null){
			sql += " and phone like ?";
			params.add("%"+employeeSearchRequest.getPhone()+"%");
		}

		if(employeeSearchRequest.getDepartmentId() != null){
			sql += " and department_id = ?";
			params.add(employeeSearchRequest.getDepartmentId());
		}

		try {
			PreparedStatement preparedStatement = BaseRepository.getConnection()
					.prepareStatement(sql);
			for (int i = 0; i < params.size(); i++) {
				preparedStatement.setObject(i + 1, params.get(i));
			}
			ResultSet rs =  preparedStatement.executeQuery();
			while (rs.next()) {
				employees.add(Employee.builder()
								.id(rs.getInt("id"))
								.name(rs.getString("name"))
								.dateOfBirth(rs.getDate("dob").toLocalDate())
								.gender(Gender.valueOf(rs.getString("gender")))
								.salary(rs.getDouble("salary"))
								.phone(rs.getString("phone"))
								.departmentId(rs.getInt("department_id"))
						.build());

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return employees;
	}

	@Override
	public Employee findById(int id) {
		String sql = "select id , name , dob , gender , salary , phone , department_id from employee where id = ?";

		try {
			PreparedStatement preparedStatement = BaseRepository.getConnection().prepareStatement(sql);
			preparedStatement.setInt(1,id);

			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()){
				return Employee.builder()
						.id(rs.getInt("id"))
						.name(rs.getString("name"))
						.dateOfBirth(rs.getDate("dob").toLocalDate())
						.salary(rs.getDouble("salary"))
						.phone(rs.getString("phone"))
						.gender(Gender.valueOf(rs.getString("gender")))
						.departmentId(rs.getInt("department_id"))
						.build();
			}
		}catch (SQLException e){
			throw new RuntimeException(e);
		}

		return null;
	}

	@Override
	public Employee save(Employee employee) {
		try {
			if(findById(employee.getId()) != null){
				String sql = "insert into employee(name , dob , gender , salary , phone , department_id) values(?,?,?,?,?,?)";
				PreparedStatement preparedStatement = BaseRepository.getConnection().prepareStatement(sql);
				preparedStatement.setString(1,employee.getName());
				preparedStatement.setDate(2,Date.valueOf(employee.getDateOfBirth()));
				preparedStatement.setString(3,employee.getGender().toString());
				preparedStatement.setDouble(4,employee.getSalary());
				preparedStatement.setString(5,employee.getPhone());
				preparedStatement.setInt(6,employee.getDepartmentId());
				preparedStatement.executeUpdate();
			}else{
				String sql = "update employee set name = ? , dob = ? , gender = ? , salary = ? , phone = ? , department_id = ? where id = ?";
				PreparedStatement preparedStatement = BaseRepository.getConnection().prepareStatement(sql);
				preparedStatement.setString(1,employee.getName());
				preparedStatement.setDate(2,Date.valueOf(employee.getDateOfBirth()));
				preparedStatement.setString(3,employee.getGender().toString());
				preparedStatement.setDouble(4,employee.getSalary());
				preparedStatement.setString(5,employee.getPhone());
				preparedStatement.setInt(6,employee.getDepartmentId());
				preparedStatement.setInt(7,employee.getId());
				preparedStatement.executeUpdate();
			}
		}catch (SQLException e){
			throw new RuntimeException(e);
		}
		return employee;
	}

	@Override
	public void delete(int id) {
		String sql = "delete from employee where id = ?";
		try {
			PreparedStatement preparedStatement = BaseRepository.getConnection().prepareStatement(sql);
			preparedStatement.setInt(1,id);
			preparedStatement.executeUpdate();
		}catch (SQLException e){
			throw new RuntimeException(e);
		}
	}
}
