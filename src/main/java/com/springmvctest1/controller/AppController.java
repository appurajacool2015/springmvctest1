package com.springmvctest1.controller;

import java.util.List;
import java.util.Locale;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.springmvctest1.model.Employee;
import com.springmvctest1.service.EmployeeService;

/**
 * @author AN298112
 *
 */
@Controller
@RequestMapping("/")
public class AppController {

	@Autowired
	EmployeeService service;
	
	@Autowired
	MessageSource messageSource;
	
	@RequestMapping(value = {"/", "/list"}, method = RequestMethod.GET)
	public String listEmployees(ModelMap model){
		
		List<Employee> employee = service.findAllEmployees();
		model.addAttribute("employees", employee);
		return "allemployees";
	}

	@RequestMapping(value = {"/new"}, method = RequestMethod.GET)
	public String newEmployee(ModelMap model) {
		Employee employee = new Employee();
		model.addAttribute("employee", employee);
		model.addAttribute("edit", false);
		return "registration";
	}
	
	/**
	 * This method will be called on form submission, handling POST request for 
	 * updating employee in database. It also validates the user input
 	 *
	 */
	@RequestMapping(value = {"/edit-{ssn}-employee"}, method = RequestMethod.GET)
	public String updateEmployee(@Valid @ModelAttribute Employee employee, BindingResult result,
			ModelMap model, @PathVariable String ssn) {
		
		if(result.hasErrors()) {
			return "registration";
		}
		if(!service.isEmployeeSsnUnique(employee.getId(), employee.getSsn())){
			FieldError ssnError = new FieldError("employee", "ssn", messageSource.getMessage("non.unique.ssn", new String[]{employee.getSsn()}, Locale.getDefault()));
			result.addError(ssnError);
			return "registration";
		}
		service.saveEmployee(employee);
		model.addAttribute("success", "Employee "  + employee.getName() + "  registered successfully");
		return "success";
	}
	
	@RequestMapping(value = {"/edit-employee-{ssn}"}, method = RequestMethod.GET)
	public String editEmployee(@ModelAttribute Employee employee, BindingResult result,
			ModelMap model, @PathVariable String ssn) {
		
		if(result.hasErrors()) {
			return "registration";
		}
		if(!service.isEmployeeSsnUnique(employee.getId(), employee.getSsn())){
			FieldError ssnError = new FieldError("employee", "ssn", messageSource.getMessage("non.unique.ssn", new String[]{employee.getSsn()}, Locale.getDefault()));
			result.addError(ssnError);
			return "registration";
		}
		service.saveEmployee(employee);
		model.addAttribute("success", "Employee "  + employee.getName() + "  registered successfully");
		return "success";
	}

	@RequestMapping(value = {"/delete-{ssn}-employee"}, method = RequestMethod.GET)
	public String deleteEmployee(@PathVariable String ssn){
		service.deleteEmployeeBySsn(ssn);
		return "redirect:/list";
	}
	
}
