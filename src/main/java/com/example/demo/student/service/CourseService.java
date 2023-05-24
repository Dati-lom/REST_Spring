package com.example.demo.student.service;


import com.example.demo.student.module.Course;
import com.example.demo.student.repository.CourseRegistrationRepository;
import com.example.demo.student.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CourseService {


    private final CourseRepository courseRepository;
    private final CourseRegistrationRepository courseRegistrationRepository;
    @Autowired
    public CourseService(CourseRepository courseRepository, CourseRegistrationRepository courseRegistrationRepository) {
        this.courseRepository = courseRepository;
        this.courseRegistrationRepository = courseRegistrationRepository;
    }


    public List<Course> getCourses(){
        return courseRepository.findAll();
    }

    public Course getCourse(long id){
        Optional<Course> courseOpt = courseRepository.findById(id);

        return courseOpt.orElseThrow(()-> new IllegalStateException("No such student")) ;
    }

    public void addCourse(Course course){
        Optional<Course> courseopt = courseRepository.findByName(course.getName());
        if(courseopt.isPresent()){
            throw new IllegalStateException("Course name already taken");
        }else
         courseRepository.save(course);
    }

    public void deleteCourse(long id){
        Optional<Course> courseOpt =  courseRepository.findById(id);
        courseRepository.delete(courseOpt.orElseThrow(()->new IllegalStateException("no such course")));
        courseRegistrationRepository.deleteAllByCourse_Id(id);
    }

    @Transactional
    public void updateCourse(long id, String name ){
        Course course = courseRepository.findById(id).orElseThrow(()->new IllegalStateException("no souch course eists"));
        course.setName(name);
    }


}
