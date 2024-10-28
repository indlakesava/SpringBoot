package com.example.SpringBootCrud.Service;

import com.example.SpringBootCrud.Entity.Student;
import com.example.SpringBootCrud.Repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    public List<Student> getAllStudents(){
        return studentRepository.findAll();
    }

    public Optional<Student> getStudentById(int id){
        return studentRepository.findById(id);
    }

    public Student saveStudent(Student student){
        return studentRepository.save(student);
    }

    public Student updateStudent(int id, Student student){
        Optional<Student> existingStudentById = studentRepository.findById(id);
        Student existingStudent;
        if(existingStudentById.isPresent()){
            existingStudent = existingStudentById.get();
        } else{
            existingStudent = new Student();
        }
        existingStudent.setAge(student.getAge());
        existingStudent.setName(student.getName());

        return studentRepository.save(existingStudent);
    }

    public void deleteStudent(int id){
        studentRepository.deleteById(id);
    }
}
