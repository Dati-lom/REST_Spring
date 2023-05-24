package com.example.demo.student.controller;


import com.example.demo.student.module.CourseRegistration;
import com.example.demo.student.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/register")
public class RegistrationController {
    private final RegistrationService registrationService;

    @Autowired
    public RegistrationController(RegistrationService registrationService){
        this.registrationService = registrationService;
    }


    @GetMapping("/getAll")
    public List<CourseRegistration> getRegistrations(){
        return registrationService.getAll();
    }

    @GetMapping("/get/{id}")
    public CourseRegistration getRegistration(@PathVariable long id){
        return registrationService.getById(id);
    }

    @PostMapping("/create")
    public void addRegistration(@RequestBody CourseRegistration registration){
        registrationService.AddById(registration);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(long id){
        registrationService.delete(id);
    }

    @PutMapping("put/{id}")
    public void uptadeRegsitration(@PathVariable long id,@RequestParam(required = false) long courseId,@RequestParam(required = false) long studentId){
        registrationService.updateReg(id,studentId,courseId);
    }
}
