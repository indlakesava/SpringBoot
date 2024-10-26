package com.example.pagination;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class StudentService {
    @Autowired
    private StudentRepository repository;
Random random = new Random();

    //@PostConstruct
    private void init(){
        List<Student> students = IntStream.rangeClosed(1, 200)
                        .mapToObj(i -> new Student(i, "Student-"+i, random.nextDouble(100), Grade.randomGrade()))
                .collect(Collectors.toList());
        students.forEach(System.out::println);
        repository.saveAll(students);
    }

    public List<Student> findAll(){
        return repository.findAll();
    }

    public List<Student> findAllStudentsBySort(String field) {
        return repository.findAll(Sort.by(Sort.Direction.ASC,field));
    }

    public Page findAllStudentsByPagination(int offset, int size) {
        Pageable pageable = PageRequest.of(offset, size);
        return repository.findAll(pageable);
    }

    public Page findAllStudentsBySortAndPagination(int offset, int size, String field) {
        Pageable pageable = PageRequest.of(offset, size).withSort(Sort.by(field));
        return repository.findAll(pageable);
    }
}
