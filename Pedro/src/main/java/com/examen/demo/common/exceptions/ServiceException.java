package com.examen.demo.common.exceptions;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
public class ServiceException extends Exception {
	
	private String code;
	
	public ServiceException(String code) {
		super();
		this.code = code;
	}
}
