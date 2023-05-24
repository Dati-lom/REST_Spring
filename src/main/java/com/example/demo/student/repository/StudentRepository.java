package com.example.demo.student.repository;

import com.example.demo.student.module.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository // Responsible for data access
public interface StudentRepository extends JpaRepository<Student, Long> {
    Optional<Student> findById(long id);

    Optional<Student> findByEmail(String email);
}
