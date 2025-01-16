package com.example.Employees.Services;

import com.example.Employees.DTO.EmployeeRequest;
import com.example.Employees.Entities.Department;
import com.example.Employees.Entities.Employee;
import com.example.Employees.Repositories.DepartmentRepository;
import com.example.Employees.Repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    public List<Employee> getAllEmployees(){
        return employeeRepository.findAll();
    }

    public Optional<Employee> getEmployeeById(long id){
        return employeeRepository.findById(id);
    }

    public Employee saveEmployee(EmployeeRequest employeeRequest){
        Department department = departmentRepository.findById(employeeRequest.getDepartmentId())
                .orElseThrow(() -> new RuntimeException("Department not found"));

        Employee employee = new Employee();
        employee.setName(employeeRequest.getName());
        employee.setDepartment(department);

        return employeeRepository.save(employee);
    }

    public Employee updateEmployee(long id, Employee employee){
        Optional<Employee> existingEmployeeById = employeeRepository.findById(id);
        Employee existingEmployee;
        if(existingEmployeeById.isPresent()){
            existingEmployee = existingEmployeeById.get();
        } else{
            existingEmployee = new Employee();
        }
        existingEmployee.setName(employee.getName());

        return employeeRepository.save(existingEmployee);
    }

    public void deleteEmployee(long id){
        employeeRepository.deleteById(id);
    }
}
