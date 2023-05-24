package com.example.demo.student.repository;

import com.example.demo.student.module.Course;
import com.example.demo.student.module.CourseRegistration;
import com.example.demo.student.module.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface CourseRegistrationRepository extends JpaRepository<CourseRegistration,Long> {



    boolean existsByStudent_Id(long id);
    void deleteAllByStudent_Id(long id);

    void deleteAllByCourse_Id(long id);
    boolean existsByCourse_Id(long id);





    Optional<CourseRegistration> findByCourse_IdAndStudent_Id(long course_id, long student_id);
    boolean existsByCourse_IdAndStudent_Id(long course_id, long stud_id);


    List<CourseRegistration> findAllByCourse_Id(long course);
    List<CourseRegistration> findAllByStudent_Id(long student);




    void deleteAllByStudentId(long id);
}
