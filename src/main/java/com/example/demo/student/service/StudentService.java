package com.example.demo.student.service;

import com.example.demo.student.module.Student;
import com.example.demo.student.repository.CourseRegistrationRepository;
import com.example.demo.student.repository.StudentRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final CourseRegistrationRepository registration;

    @Autowired
    public StudentService(StudentRepository studentRepository, CourseRegistrationRepository registration) {
        this.studentRepository = studentRepository;
        this.registration = registration;
    }

    public List<Student> getStudents() {
        return studentRepository.findAll();
    }

    public Student getStudent(long id){
        Optional<Student> studentOpt = studentRepository.findById(id);

        return studentOpt.orElseThrow(() -> new RuntimeException("No such student exists: " + id));
    }

    public void addStudent(Student student){
        Optional<Student> studentbyemail = studentRepository.findByEmail(student.getEmail());
        if(studentbyemail.isPresent())
            throw new IllegalStateException("email taken");
        else studentRepository.save(student);
    }

    public void deleteStudent(long id){
        boolean studentd = studentRepository.existsById(id);

        if(!studentd){
            throw new IllegalStateException("such student does not exist");
        }else studentRepository.deleteById(id);

        if(registration.existsByStudent_Id(id)){
            registration.deleteAllByStudentId(id);
        }
    }

    @Transactional
    public void replaceStudent(Long id, String name, LocalDate Dob, String email){

        Student student = studentRepository.findById(id).orElseThrow(()->
                new IllegalStateException("No such student exists"));
        Optional<Student> studOpt = studentRepository.findByEmail(email);
        student.setName(name);
        student.setDob(Dob);
        if(studOpt.isEmpty()){
            student.setEmail(email);
        }
    }


}
