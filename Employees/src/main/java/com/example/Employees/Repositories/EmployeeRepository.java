package com.example.Employees.Repositories;

import com.example.Employees.Entities.Department;
import com.example.Employees.Entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    @Query("SELECT e FROM Employee e WHERE e.department.name = :departmentName ORDER BY e.employeeId")
    List<Employee> findEmployeesByDepartmentName(@Param("departmentName") String departmentName);
}
