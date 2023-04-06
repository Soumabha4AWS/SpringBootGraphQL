package com.springboot.SampleRestAPIWithMySqlDatabase.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.springboot.SampleRestAPIWithMySqlDatabase.entity.CourseEntity;
import com.springboot.SampleRestAPIWithMySqlDatabase.service.CourseService;

import java.util.List;

@RestController
public class CourseController {
	
	
	
	@Autowired
    private CourseService service;
	
    @PostMapping("/post")
    public CourseEntity addCourse(@RequestBody CourseEntity course) {
        return service.saveCourse(course);
    }

    @PostMapping("/posts")
    public List<CourseEntity> addCourses(@RequestBody List<CourseEntity> courses) {
        return service.saveAllCourses(courses);
    }

    @GetMapping("/get/all")
    public List<CourseEntity> findAllCourses() {
        return service.getAllCourses();
    }

    @GetMapping("/get/{id}")
    public CourseEntity findCourseById(@PathVariable String id) {
        return service.getCourseById(id);
    }

    @GetMapping("/get/course/{courseName}")
    public CourseEntity findCourseByName(@PathVariable String courseName) {
        return service.getCourseByName(courseName);
    }

    @PutMapping("/put/{id}")
    public CourseEntity updateCourse(@PathVariable String id,@RequestBody CourseEntity course) {
        return service.updateCourseById(id,course);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteCourse(@PathVariable String id) {
        return service.deleteCourseById(id);
    }
}
