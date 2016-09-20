package com.springmvctest1.dao;

import java.util.List;

import com.springmvctest1.model.Employee;

public interface EmployeeDao {
	
	Employee findById(int id);
	
	void saveEmployee(Employee employee);
	
	void deleteEmployee(Employee employee);
	
	List<Employee> findAllEmployees();
	
	Employee findEmployeeBySsn(String ssn);
}
