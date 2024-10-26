package com.example.pagination;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/test")
    public String test(){
        return "Welcome to Student Service!";
    }

    @GetMapping("/student")
    public List<Student> findAllStudents(){
        return studentService.findAll();
    }

    @GetMapping("/student/sort/{field}")
    public List<Student> findAllStudentsBySort(@PathVariable String field){
        return studentService.findAllStudentsBySort(field);
    }

    @GetMapping("/student/pagination/offset/{offset}/size/{size}")
    public Page<Student> findAllStudentsBySort(@PathVariable int offset, @PathVariable int size){
        return studentService.findAllStudentsByPagination(offset, size);
    }

    @GetMapping("/student/pagination/offset/{offset}/size/{size}/sort/{field}")
    public Page<Student> findAllStudentsBySortAndPagination(@PathVariable int offset, @PathVariable int size, @PathVariable String field){
        return studentService.findAllStudentsBySortAndPagination(offset, size, field);
    }
}
