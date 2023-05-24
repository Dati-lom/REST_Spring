package com.example.demo.student.repository;

import com.example.demo.student.module.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

public Optional<Course> findByName(String name);

    public boolean existsCourseByName(String name);

    void deleteByName(String name);
}


