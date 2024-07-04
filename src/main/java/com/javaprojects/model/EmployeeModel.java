package com.javaprojects.model;

import lombok.Data;

@Data
public class EmployeeModel {
    private Long id;
    private String name;
    private int salary;
    private String department;
}
