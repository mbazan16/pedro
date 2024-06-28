package com.examen.demo.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.examen.demo.common.exceptions.MsgErrors;
import com.examen.demo.controllers.exceptions.AddEmployeeControllerException;

import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@Slf4j
public class GlobalExceptionsHandler {
	
	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public String exceptionGeneralHandler(Model model) {
		log.info("[exceptionGeneralHandler]");
		model.addAttribute("msgError",MsgErrors.ERROR_GENERAL); // msgError = error.general		
		return "error";
		
		
	}
	
	@ExceptionHandler(AddEmployeeControllerException.class)	
	public String addEmployeeControllerHandler(Model model) {
		log.info("[addEmployeeControllerHandler]");		
		model.addAttribute("msgError",MsgErrors.ERROR_EMPLOYEE_NAME); // msgError = error.employee.name		
		return "employees";
		
		
	}

}
