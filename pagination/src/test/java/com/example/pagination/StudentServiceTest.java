package com.example.pagination;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.core.AutoConfigureCache;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class StudentServiceTest {
    @Autowired
    private StudentService studentService;

    @MockBean
    private StudentRepository studentRepository;

    @Test
    public void getStudentsTest(){
        when(studentRepository.findAll()).thenReturn(Stream.of(
                new Student(1, "Lakshmi", 89.91, Grade.A),
                new Student(2, "Test", 50.25, Grade.B),
                new Student(3, "OMG", 95.70, Grade.A)
        ).collect(Collectors.toList()));

        assertEquals(3, studentService.findAll().size());
    }

}
