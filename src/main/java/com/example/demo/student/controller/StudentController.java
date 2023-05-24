package com.example.demo.student.controller;

import com.example.demo.student.repository.StudentRepository;
import com.example.demo.student.service.StudentService;
import com.example.demo.student.module.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/student")

public class StudentController {

    private final StudentService studentService;
    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/getAll")
    public List<Student> getStudents(){
        return  studentService.getStudents();
    }

    @GetMapping("/get/{id}")
    public Student getStudent(@PathVariable long id){
        return  studentService.getStudent(id);
    }

    @PostMapping("/create")
    public void register(@RequestBody Student student){

        studentService.addStudent(student);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteStudent(@PathVariable("id") Long id){
        studentService.deleteStudent(id);
    }

    @PutMapping("/put/{id}")
    public void updateStudent(@PathVariable("id") Long id, @RequestParam(required = false) String name,
                              @RequestParam(required = false)String email,
                              @RequestParam(required = false)LocalDate Dob){
        studentService.replaceStudent(id,name,Dob,email);
    }

}
