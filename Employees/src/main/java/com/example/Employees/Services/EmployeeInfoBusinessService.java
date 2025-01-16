package com.example.Employees.Services;

import com.example.Employees.Entities.Department;
import com.example.Employees.Entities.Employee;
import com.example.Employees.Repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeInfoBusinessService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public List<Employee> findEmployeesByDepartmentName(String departmentName){
        return employeeRepository.findEmployeesByDepartmentName(departmentName);
    }
}
