package com.examen.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.examen.demo.common.EmployeeState;
import com.examen.demo.common.exceptions.ServiceException;
import com.examen.demo.entities.Employee;
import com.examen.demo.services.IEmployeesService;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/employees")
@Slf4j
public class EmployeesController {
	
	@Autowired
	IEmployeesService service;
	
	@GetMapping
	public String listEmployees(Model model) throws ServiceException {
		log.info("[listEmployees]");
		
		try {
			List<Employee> employees = service.listAll();
			model.addAttribute("employees",employees);
		} catch (ServiceException se) {
			model.addAttribute("error",true);
		}
		return "employees";
	}
	
	@GetMapping("/{id}")
	public String employeeDetails(Model model,@PathVariable Integer id) throws ServiceException {
		log.info("[listEmployees]");
		log.debug("["+"Id: "+id+"]");
		
		Employee employee = service.getEmployee(id);
		model.addAttribute("employee", employee);
		model.addAttribute("states",EmployeeState.values());
		return "employee";
	}
	
	@PostMapping
	public String addEmployee(Model model,Employee employee) throws ServiceException{
		log.info("[addEmployee]");
		log.debug("["+"Employee: "+employee.toString()+"]");
		
		employee = service.save(employee);
		model.addAttribute(employee);
		return "employees";
	}
	
	@PostMapping("/mod/{id}")
	public String modifyEmployee(Employee employee) throws ServiceException{
		log.info("[modifyEmployee]");
		log.debug("["+"Employee: "+employee.toString()+"]");
		
		employee = service.modify(employee);
		return "redirect:/employees";
	}
	
	@GetMapping("/d/{id}")
	public String deleteEmployee(@PathVariable Integer id) throws ServiceException{
		log.info("[deleteEmployee]");
		log.debug("["+"Id: "+id+"]");
		service.delete(id);
		return "redirect:/employees";
	}
	
}
