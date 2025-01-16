package com.example.Employees.Services;

import com.example.Employees.Entities.Department;
import com.example.Employees.Repositories.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentService {
    @Autowired
    private DepartmentRepository departmentRepository;

    public List<Department> getAllDepartments(){
        return departmentRepository.findAll();
    }

    public Optional<Department> getDepartmentById(long id){
        return departmentRepository.findById(id);
    }

    public Department saveDepartment(Department department){
        return departmentRepository.save(department);
    }

    public Department updateDepartment(long id, Department department){
        Optional<Department> existingDepartmentById = departmentRepository.findById(id);
        Department existingDepartment;
        if(existingDepartmentById.isPresent()){
            existingDepartment = existingDepartmentById.get();
        } else{
            existingDepartment = new Department();
        }
        existingDepartment.setName(department.getName());

        return departmentRepository.save(existingDepartment);
    }

    public void deleteDepartment(long id){
        departmentRepository.deleteById(id);
    }
}
