package com.example.Employees.Controllers;

import com.example.Employees.Entities.Department;
import com.example.Employees.Services.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/department")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @GetMapping("/")
    public ResponseEntity<List<Department>> getDepartments(){
        return ResponseEntity.ok(departmentService.getAllDepartments());
    }

    @GetMapping("/departmentById/{id}")
    public ResponseEntity<Department> getDepartmentsById(@PathVariable int id ){
        Optional<Department> department = departmentService.getDepartmentById(id);
        return department.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/")
    public ResponseEntity<Department> insertDepartment(@RequestBody Department department){
        System.out.println("Save Hit");
        departmentService.saveDepartment(department);
        return ResponseEntity.status(HttpStatus.CREATED).body(department);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Department> updateDepartment(@PathVariable int id, @RequestBody Department department){
        Department updatedDepartment = departmentService.updateDepartment(id, department);
        return ResponseEntity.status(HttpStatus.OK).body(updatedDepartment);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable int id){
        if(departmentService.getDepartmentById(id).isPresent()){
            departmentService.deleteDepartment(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
