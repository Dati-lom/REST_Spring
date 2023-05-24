package com.example.demo.student.module;


import jakarta.persistence.*;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "course")
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Course {
    @Id
    @GeneratedValue
    private long id;

    @NotNull
    private String name;


    public Course(String name){
        this.name = name;
    }

    public Course(String name,Set<CourseRegistration> stud){
        this.name = name;
    }

//    public void addToJoinstud(CourseRegistration studentCourse ) {
//        if (courses.contains(studentCourse)) {
//            throw new RuntimeException("this mapping already exists");
//        } else
//            courses.add(studentCourse);
//    }
//
//    public  void addAll(Set<CourseRegistration> set){
//        for(CourseRegistration x : set){
//            addToJoinstud(x);
//        }
//    }
}
