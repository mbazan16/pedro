package com.examen.demo.services;

import java.util.List;

import com.examen.demo.common.exceptions.ServiceException;
import com.examen.demo.entities.Employee;

public interface IEmployeesService {

	public List<Employee> listAll() throws ServiceException;

	public Employee getEmployee(Integer id) throws ServiceException;

	public Employee save(Employee employee) throws ServiceException ;

	public Employee modify(Employee employee) throws ServiceException;

	public void delete(Integer id) throws ServiceException;

}
