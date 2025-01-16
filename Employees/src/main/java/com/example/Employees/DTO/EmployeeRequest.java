package com.example.Employees.DTO;

import lombok.Data;

@Data
public class EmployeeRequest {
    private String name;
    private Long departmentId;
}
