package com.example.demo.student.service;

import com.example.demo.student.module.Course;
import com.example.demo.student.module.CourseRegistration;
import com.example.demo.student.module.Student;
import com.example.demo.student.repository.CourseRegistrationRepository;
import com.example.demo.student.repository.CourseRepository;
import com.example.demo.student.repository.StudentRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RegistrationService {
    private final CourseRegistrationRepository registration;

    private final CourseRepository courseRepository;
    private final StudentRepository studentRepository;

    @Autowired
    public RegistrationService(CourseRegistrationRepository registration, CourseRepository courseRepository, StudentRepository studentRepository){
        this.courseRepository = courseRepository;
        this.studentRepository = studentRepository;
        this.registration = registration;
    }

    public List<CourseRegistration> getAll(){
        return registration.findAll();
    }


    public CourseRegistration getById(long id){
        Optional<CourseRegistration> regOpt = registration.findById(id);

        return regOpt.orElseThrow(()-> new IllegalStateException("No such registration"));
    }

    public CourseRegistration getByStudentCourse(long stud_id, long course_id){
        Optional<CourseRegistration> regOpt = registration.findByCourse_IdAndStudent_Id(course_id,stud_id);

        return regOpt.orElseThrow(()->new IllegalStateException("no such registration"));

    }

    public void AddById(CourseRegistration reg){
//        Optional<Course> courseOpt = courseRepository.findById(course_id);
//        Optional<Student> studentOpt = studentRepository.findById(stud_id);
//
//        Optional<CourseRegistration> regOpt = registration.findByCourse_IdAndStudent_Id(course_id,stud_id);
//
//        if(regOpt.isPresent())throw new IllegalStateException("registration already exists");
//
//        else registration.save(new CourseRegistration(
//                studentOpt.orElseThrow(()->new IllegalStateException("No student with this id exists")),
//                courseOpt.orElseThrow(()->new IllegalStateException("No course with this id exists"))
//        ));
//        Course course = courseRepository.findById(reg.getCourse().getId()).orElseThrow(()->new IllegalStateException("no such course"));
//        Student student = studentRepository.findById(reg.getStudent().getId()).orElseThrow(()->new IllegalStateException("no such student"));
        Course course = reg.getCourse();
        Student student = reg.getStudent();
        if(course == null || student == null){
            throw new IllegalStateException("Check inputs");
        }else if(registration.existsByCourse_IdAndStudent_Id(course.getId(),student.getId())){
            throw  new IllegalStateException("This registration already exists");
        }else if(!courseRepository.existsById(course.getId()) || !studentRepository.existsById(student.getId())){
            throw new IllegalStateException("Course or Student does not exist");

        }
        registration.save(reg);
    }


    public void delete(long id){
        boolean existsReg = registration.existsById(id);

        if(existsReg){
            registration.deleteById(id);
        }else throw new IllegalStateException("such registration does not exist");

    }

    @Transactional
    public void updateReg(long reg_id, long stud_id, long course_id){
        CourseRegistration reg = registration.findById(reg_id).
                orElseThrow(()->new IllegalStateException("No such Registration exists"));
        Course course = courseRepository.findById(course_id).
                orElseThrow(()->new IllegalStateException("No such Course exists"));
        Student student = studentRepository.findById(stud_id).
                orElseThrow(()->new IllegalStateException("No such Student exists"));

        reg.setCourse(course);
        reg.setStudent(student);

    }





}
