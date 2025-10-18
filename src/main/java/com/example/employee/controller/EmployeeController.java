package com.example.employee.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.employee.model.Employee;
import com.example.employee.repository.EmployeeRepository;

import jakarta.validation.Valid;

@Controller
public class EmployeeController {
	
	private final EmployeeRepository repository;
	
	public EmployeeController(EmployeeRepository repository) {
		this.repository = repository;
	}
	
	@GetMapping("/employees")
	public String list(@RequestParam(name = "keyword", required = false) String keyword, Model model) {
		List<Employee> employees;
		if (keyword != null && !keyword.isEmpty()) {
			employees = repository.findByNameContainingIgnoreCase(keyword);
		}else {
			employees = repository.findAll();
		}		
		model.addAttribute("employees", employees);
		model.addAttribute("keyword", keyword);
		return "employee-list";
	}
	@GetMapping("/employees/new")
	public String newForm(Model model) {
		model.addAttribute("employee", new Employee());
		return "employee-form";
	}
	@PostMapping("/employees")
	public String save(@Valid @ModelAttribute("employee") Employee employee, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			return "employee-form";
		}
		repository.save(employee);
		return "redirect:/employees";
	}
	//編集フォームの表示
	@GetMapping("/employees/edit/{id}")
	public String edit(@PathVariable Long id, Model model) {
		Employee employee = repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid employee Id:" + id));
		model.addAttribute("employee",employee);
		return "employee-form";
	}
	//更新処理
	@PostMapping("/employees/{id}")
	public String update(@PathVariable Long id, @Valid @ModelAttribute("employee") Employee employee,BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			return "employee-form";
		}
		employee.setId(id);
		repository.save(employee);
		return "redirect:/employees";
	}
	//削除処理
	@GetMapping("/employees/delete/{id}")
	public String delete(@PathVariable Long id) {
		repository.deleteById(id);
		return "redirect:/employees";
	}
}
