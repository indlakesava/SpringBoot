package com.example.SpringBootCrud;

import com.example.SpringBootCrud.Entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestH2Repository extends JpaRepository<Student, Integer> {
}
