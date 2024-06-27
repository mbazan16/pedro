package com.examen.demo.entities;

import com.examen.demo.common.EmployeeState;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Employee {
	
	@Id
	private int id;
	
	private String name;
	@Enumerated(EnumType.ORDINAL)
	private EmployeeState state;
	
}
