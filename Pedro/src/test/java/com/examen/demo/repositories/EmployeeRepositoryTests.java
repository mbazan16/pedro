package com.examen.demo.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.examen.demo.common.EmployeeState;
import com.examen.demo.entities.Employee;

import jakarta.transaction.Transactional;

@DataJpaTest
@Transactional
@Rollback
public class EmployeeRepositoryTests {
	
	@Autowired
	EmployeeRepository repository;
	
	@Test
    public void findByNameLike() {
		Employee emp1= new Employee();
		emp1.setName("pepe");
		emp1.setState(EmployeeState.EMPLOYED);
		Employee emp2= new Employee();
		emp2.setName("pepe");
		emp2.setState(EmployeeState.EMPLOYED);
		Employee emp3= new Employee();
		emp3.setName("ramon");
		emp3.setState(EmployeeState.EMPLOYED);
		
		repository.save(emp1);
		repository.save(emp2);
		repository.save(emp3);
		
		List<Employee> employees = repository.findByName("pep");
        assertThat(employees).hasSize(2);
    }
}
