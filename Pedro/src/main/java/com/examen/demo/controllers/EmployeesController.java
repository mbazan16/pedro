package com.examen.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.examen.demo.common.EmployeeState;
import com.examen.demo.controllers.exceptions.AddEmployeeControllerException;
import com.examen.demo.controllers.exceptions.ControllerException;
import com.examen.demo.entities.Employee;
import com.examen.demo.services.IEmployeesService;

import static com.examen.demo.controllers.Views.*;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/employees")
@Slf4j
public class EmployeesController {
	
	@Autowired
	IEmployeesService service; 
	
	@GetMapping
	public String searchEmployees(Model model,@RequestParam(name="name", required=false) String name) throws Exception {
		log.info("[searchEmployees]");
		log.debug("["+"Name: "+name+"]");
		
		List<Employee> employees = service.searchEmployees(name);
		model.addAttribute("employees",employees);
		
		return VIEW_EMPLOYEES;
	}
	
	@GetMapping("/{id}")
	public String employeeDetails(Model model,@PathVariable Integer id) throws Exception {
		log.info("[listEmployees]");
		log.debug("["+"Id: "+id+"]");
		
		Employee employee = service.getEmployee(id);
		model.addAttribute("employee", employee);
		model.addAttribute("states",EmployeeState.values());
		return VIEW_EMPLOYEE;
	}
	
	@PostMapping
	public String addEmployee(Model model,@Valid Employee employee, BindingResult result) throws Exception{
		log.info("[addEmployee]");
		log.debug("["+"Employee: "+employee.toString()+"]");
		if (result.hasErrors()) throw new AddEmployeeControllerException();
		service.save(employee);
		log.debug("["+"Employee: "+employee.toString()+"]");
		return VIEW_EMPLOYEES;
	}
	
	@PostMapping("/mod/{id}")
	public String modifyEmployee(Employee employee) throws Exception{
		log.info("[modifyEmployee]");
		log.debug("["+"Employee: "+employee.toString()+"]");
		
		employee = service.modify(employee);
		return "redirect:/employees";
	}
	
	@GetMapping("/d/{id}")
	public String deleteEmployee(@PathVariable Integer id) throws Exception{
		log.info("[deleteEmployee]");
		log.debug("["+"Id: "+id+"]");
		service.delete(id);
		return "redirect:/employees";
	}
	
}
