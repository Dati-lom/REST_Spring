package com.example.demo.student.controller;


import com.example.demo.student.module.Course;
import com.example.demo.student.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/course")
public class CourseController {
    private final CourseService courseService;


    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }


    @GetMapping("/getAll")
    public List<Course> getCourses() {
        return courseService.getCourses();
    }

    @GetMapping("/get/{id}")
    public Course getCourse(@PathVariable Long id) {
        return courseService.getCourse(id);
    }

    @PostMapping("/create")
    public void addCourse(@RequestBody Course course) {
        courseService.addCourse(course);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteCourse(@PathVariable("id") long id) {
        courseService.deleteCourse(id);
    }

    @PutMapping("put/{id}")
    public void updateCourse(@PathVariable("id") long id, @RequestParam(required = false) String name) {
        courseService.updateCourse(id, name);
    }
}
