package com.example.employee.model;

import java.time.LocalDate;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.Data;

@Entity
@Data
public class Employee {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "{NotBlank.employee.name}")
	private String name;
	@NotBlank(message = "{NotBlank.employee.department")
	private String department;
	@NotNull(message = "{NotBlank.employee.hireDate}")
	@PastOrPresent(message = "{PastOrPresent.employee.hireDate}")
	private LocalDate hireDate;
	
	//Getter/Setter
	public Long getId() { return id; }
	public void  setId(Long id) { this.id = id;}
	
	public String getName() { return name; }
	public void  setName(String name) { this.name = name;}
	
	public String getDepartment() { return department; }
	public void  setDepartment(String department) { this.department = department;}
	
	public LocalDate getHireDate() { return hireDate; }
	public void  setHireDate(LocalDate hireDate) { this.hireDate = hireDate;}
		
}
