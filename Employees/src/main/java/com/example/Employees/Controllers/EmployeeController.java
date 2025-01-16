package com.example.Employees.Controllers;

import com.example.Employees.DTO.EmployeeRequest;
import com.example.Employees.Entities.Employee;
import com.example.Employees.Services.EmployeeInfoBusinessService;
import com.example.Employees.Services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    private EmployeeInfoBusinessService employeeInfoBusinessService;

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/hello")
    public String hello(){
        return "Hello World!";
    }

    @GetMapping("/by-department/{name}")
    public List<Employee> getEmployeesByDepartmentName(@PathVariable("name") String departmentName){
        return employeeInfoBusinessService.findEmployeesByDepartmentName(departmentName);
    }

    @GetMapping("/")
    public ResponseEntity<List<Employee>> getEmployees(){
        return ResponseEntity.ok(employeeService.getAllEmployees());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeesById(@PathVariable int id ){
        Optional<Employee> employee = employeeService.getEmployeeById(id);
        return employee.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/")
    public ResponseEntity<Employee> insertEmployee(@RequestBody EmployeeRequest employeeRequest){
        System.out.println("Save Hit");
        employeeService.saveEmployee(employeeRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable int id, @RequestBody Employee employee){
        Employee updatedEmployee = employeeService.updateEmployee(id, employee);
        return ResponseEntity.status(HttpStatus.OK).body(updatedEmployee);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable int id){
        if(employeeService.getEmployeeById(id).isPresent()){
            employeeService.deleteEmployee(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

}
