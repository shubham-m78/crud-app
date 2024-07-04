package com.javaprojects.controller;

import com.javaprojects.entity.Employee;
import com.javaprojects.model.EmployeeModel;
import com.javaprojects.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/create")
    public ResponseEntity<EmployeeModel> addEmployee(@RequestBody Employee employee) {
        EmployeeModel createdEmployee = employeeService.addEmployee(employee);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(createdEmployee);
    }

    @GetMapping("")
    public ResponseEntity<List<EmployeeModel>> getEmployees() {
        List<EmployeeModel> employees = employeeService.getEmployees();
        return ResponseEntity.status(HttpStatus.OK)
                .body(employees);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeModel> getEmployeeById(@PathVariable Long id) {
        EmployeeModel employee = employeeService.getEmployeeById(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(employee);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
        return ResponseEntity.status(HttpStatus.OK)
                .build();
    }
}
