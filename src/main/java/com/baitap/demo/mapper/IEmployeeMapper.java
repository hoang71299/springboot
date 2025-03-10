package com.baitap.demo.mapper;

import com.baitap.demo.dto.employee.EmployeeRequest;
import com.baitap.demo.dto.employee.EmployeeResponse;
import com.baitap.demo.modal.Employee;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IEmployeeMapper {
	Employee employeeRequestToEmployee(EmployeeRequest employeeRequest);
	EmployeeResponse employeeToEmployeeResponse(Employee employee);
}
