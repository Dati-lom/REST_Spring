package com.example.demo;

import com.example.demo.student.module.Course;
import com.example.demo.student.module.CourseRegistration;
import com.example.demo.student.module.Student;
import com.example.demo.student.repository.CourseRegistrationRepository;
import com.example.demo.student.repository.CourseRepository;
import com.example.demo.student.repository.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.time.LocalDate;
import java.util.*;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(StudentRepository repository, CourseRegistrationRepository registration, CourseRepository courseRepository){
		return args -> {



			Student dati = new Student(
					"Dati",
					LocalDate.of(2003, 1, 13),
					"Davit13.lomauridze@gmail.com"
			);

			Student mari = new Student(
					"Mari",
					LocalDate.of(2001, 11, 7),
					"mari7.lomauridze@gmail.com");

			Course I2I = new Course("i2i");

			Course CA = new Course("CA");

			Course A_DS = new Course("A and DS");

			Course Calculus = new Course("Calculus");




	//			CourseRegistration dregistration1 = new CourseRegistration(dati,I2I);
	//			CourseRegistration dregistration2 = new CourseRegistration(dati,CA);
	//			CourseRegistration dregistration3 = new CourseRegistration(dati,A_DS);
	//			CourseRegistration dregistration4 = new CourseRegistration(dati,Calculus);
	//
	//
	//			CourseRegistration mregistration1 = new CourseRegistration(mari,I2I);
	//			CourseRegistration mregistration2 = new CourseRegistration(mari,CA);
	//			CourseRegistration mregistration3 = new CourseRegistration(mari,A_DS);
	//			CourseRegistration mregistration4 = new CourseRegistration(mari,Calculus);


			repository.saveAll(List.of(dati,mari));


			courseRepository.saveAll(List.of(I2I,CA,A_DS,Calculus));
//			registration.saveAll(List.of(dregistration1,dregistration2,dregistration3,dregistration4,
//					mregistration1,mregistration2,mregistration3,mregistration4));

		};
	}

	@Bean
	public Docket docket() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.example.demo.student.controller"))
				.apis(RequestHandlerSelectors.any())
				.paths(PathSelectors.any())
				.build();
	}
}

