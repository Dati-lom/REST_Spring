package com.example.demo.student.module;


import jakarta.persistence.*;
import lombok.*;

import javax.validation.constraints.NotNull;

@Entity
@Table
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CourseRegistration {

    @Id
    @GeneratedValue
    private long just_id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    @NotNull
    @ManyToOne
    @JoinColumn(name="student_id")
    private Student student;

    public CourseRegistration(Student student,Course course){
        this.course = course;
        this.student = student;
    }


}
