package com.springmvctest1.service;

import java.util.List;

import com.springmvctest1.model.Employee;

public interface EmployeeService {
	
	Employee findById(int id);
	
	void saveEmployee(Employee employee);
	
	void updateEmployee(Employee employee);
	
	void deleteEmployee(Employee employee);
	
	void deleteEmployeeBySsn(String ssn);
	
	Employee findEmployeeBySsn(String ssn);
	
	List<Employee> findAllEmployees();
	
	boolean isEmployeeSsnUnique(Integer id, String ssn);

}
