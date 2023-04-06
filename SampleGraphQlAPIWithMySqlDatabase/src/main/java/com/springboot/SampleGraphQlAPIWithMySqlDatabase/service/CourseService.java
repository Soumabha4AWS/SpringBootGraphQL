package com.springboot.SampleGraphQlAPIWithMySqlDatabase.service;


import org.springframework.stereotype.Service;

import com.springboot.SampleGraphQlAPIWithMySqlDatabase.entity.CourseEntity;

import java.util.List;

@Service
public interface CourseService {
	    

    public CourseEntity saveCourse(CourseEntity courseEntity);
    public List<CourseEntity> saveAllCourses(List<CourseEntity> courseEntities);
    public List<CourseEntity> getAllCourses();
    public CourseEntity getCourseById(String id);
    public CourseEntity getCourseByName(String name);
    public String deleteCourseById(String id);
    public CourseEntity updateCourseById(String id,CourseEntity courseEntity);


}
