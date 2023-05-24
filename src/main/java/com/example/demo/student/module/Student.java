package com.example.demo.student.module;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "student")
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Student {

    @Id
    @GeneratedValue
    private long id;
    private String name;
    private LocalDate Dob;

    private String email;
    @Transient
    private int age;


    public Student(String name,  LocalDate dob, String email) {
        this.name = name;
        Dob = dob;
        this.email = email;
    }

    public Student(String name,  LocalDate dob, String email,Set<CourseRegistration> courses) {
        this.name = name;
        Dob = dob;
        this.email = email;
    }

    public int getAge() {
        return Period.between(Dob, LocalDate.now()).getYears();
    }

//    public void addToJoinstud(CourseRegistration studentCourse ){
//        if(students.contains(studentCourse)){
//            throw new RuntimeException("this mapping already exists");
//        }else students.add(studentCourse);
//    }
//
//    public  void addAll(Set<CourseRegistration> set){
//        for(CourseRegistration x : set){
//            addToJoinstud(x);
//        }
//    }

}
