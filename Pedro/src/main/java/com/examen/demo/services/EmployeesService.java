package com.examen.demo.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.examen.demo.common.EmployeeState;
import com.examen.demo.common.exceptions.CodeErrors;
import com.examen.demo.common.exceptions.ServiceException;
import com.examen.demo.entities.Employee;
import com.examen.demo.repositories.EmployeeRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class EmployeesService implements IEmployeesService{
	
	@Autowired
	EmployeeRepository repository;
	
	@Override
	public List<Employee> searchEmployees(String name) throws ServiceException{
		log.info("[listEmployees]");
		
		List<Employee> employees = new ArrayList<Employee>();
		try {
			name.trim().toUpperCase();
			employees = repository.findByName(name);
			if(employees.isEmpty()) throw new ServiceException(CodeErrors.EMPLOYEES_NO_ELEMENTS_FOUND);
			
		} catch(ServiceException se) {
			log.error("ServiceException",se);
			throw se;
		} catch(Exception e) {
			log.error("Exception",e);
			throw new ServiceException(CodeErrors.ERROR_GENERAL);
		}
		return employees;
	}

	@Override
	public Employee getEmployee(Integer id) throws ServiceException {
		log.info("[getEmployee]");
		log.debug("["+"Id: "+id+"]");
		
		Optional<Employee> employeeOpt = null;
		Employee employee;
		try {
			employeeOpt = repository.findById(id);
			if(!employeeOpt.isPresent()) throw new ServiceException(CodeErrors.EMPLOYEE_NOT_FOUND);
			employee = employeeOpt.get();
		} catch(ServiceException se) {
			log.error("ServiceException",se);
			throw se;
		} catch(Exception e) {
			log.error("Exception",e);
			throw new ServiceException(CodeErrors.ERROR_GENERAL);
		}
		return employee;
	}

	@Override
	public Employee save(Employee employee) throws ServiceException {
		log.info("[getEmployee]");
		log.debug("["+"Employee: "+employee+"]");
		
		try {
			employee.setState(EmployeeState.EMPLOYED);
			employee = repository.save(employee);
		} catch(Exception e) {
			log.error("Exception",e);
			throw new ServiceException(CodeErrors.ERROR_GENERAL);
		}
		return employee;
	}

	@Override
	public Employee modify(Employee employee) throws ServiceException {
		log.info("[modify]");
		log.debug("["+"Employee: "+employee+"]");
		
		try {
			employee = repository.save(employee);
		} catch(Exception e) {
			log.error("Exception",e);
			throw new ServiceException(CodeErrors.ERROR_GENERAL);
		}
		return employee;
	}

	@Override
	public void delete(Integer id) throws ServiceException {
		log.info("[modify]");
		log.debug("["+"Id: "+id+"]");
		
		try {
			repository.deleteById(id);;
		} catch(Exception e) {
			log.error("Exception",e);
			throw new ServiceException(CodeErrors.ERROR_GENERAL);
		}
	}
	
	
}
