package com.javaprojects.service;

import com.javaprojects.entity.Employee;
import com.javaprojects.exceptions.EmployeeNotFoundException;
import com.javaprojects.model.EmployeeModel;
import com.javaprojects.repository.EmployeeRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepo employeeRepo;

    @Autowired
    private ModelMapper modelMapper;

    public EmployeeService(EmployeeRepo employeeRepo) {
        this.employeeRepo = employeeRepo;
    }

    public EmployeeModel addEmployee(Employee employee) {
        Employee savedEmployee = employeeRepo.save(employee);
        return modelMapper.map(savedEmployee, EmployeeModel.class);
    }

    public List<EmployeeModel> getEmployees() {
        List<Employee> employees = employeeRepo.findAll();
        return employees.stream()
                .map(employee -> modelMapper.map(employee, EmployeeModel.class))
                .collect(Collectors.toList());
    }

    public EmployeeModel getEmployeeById(Long id) {
        Optional<Employee> employee = employeeRepo.findById(id);
        return employee
                .map(emp -> modelMapper.map(emp, EmployeeModel.class))
                .orElseThrow(() -> new EmployeeNotFoundException("Employee not found with Id: " + id));
    }

    public void deleteEmployee(Long id) {
        if (employeeRepo.existsById(id)) {
            employeeRepo.deleteById(id);
        } else {
            throw new EmployeeNotFoundException("Employee not found with Id: " + id);
        }
    }
}
