package com.javaprojects.controller;

import com.javaprojects.entity.Employee;
import com.javaprojects.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/create")
    public Employee addEmployee(@RequestBody Employee employee) {
        return employeeService.addEmployee(employee);
    }

    @GetMapping("")
    public List<Employee> getEmployees(){
        return employeeService.getEmployees();
    }
}
