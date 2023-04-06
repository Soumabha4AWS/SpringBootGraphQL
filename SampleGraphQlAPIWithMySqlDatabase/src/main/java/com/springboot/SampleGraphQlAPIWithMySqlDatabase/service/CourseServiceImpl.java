package com.springboot.SampleGraphQlAPIWithMySqlDatabase.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.SampleGraphQlAPIWithMySqlDatabase.entity.CourseEntity;
import com.springboot.SampleGraphQlAPIWithMySqlDatabase.repository.CourseRepository;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {
	
    @Autowired
    private CourseRepository courseRepository;

    public CourseEntity saveCourse(CourseEntity courseEntity) {
        return courseRepository.save(courseEntity);
    }

    public List<CourseEntity> saveAllCourses(List<CourseEntity> courseEntities) {
        return courseRepository.saveAll(courseEntities);
    }

    public List<CourseEntity> getAllCourses() {
        return courseRepository.findAll();
    }

    public CourseEntity getCourseById(String id) {
        return courseRepository.findById(id).orElse(null);
    }

    public CourseEntity getCourseByName(String name) {
        return courseRepository.findByCourseName(name);
    }

    public String deleteCourseById(String id) {
    	courseRepository.deleteById(id);
        return "CourseEntity removed !! " + id;
    }

    public CourseEntity updateCourseById(String id,CourseEntity courseEntity) {
        CourseEntity existingCourseEntity = courseRepository.findById(id).orElse(null);
        existingCourseEntity.setCourseName(courseEntity.getCourseName());
        existingCourseEntity.setRating(courseEntity.getRating());
        existingCourseEntity.setPrice(courseEntity.getPrice());
        existingCourseEntity.setTrainerName(courseEntity.getTrainerName());
        existingCourseEntity.setNumberOfDays(courseEntity.getNumberOfDays());        
        return courseRepository.save(existingCourseEntity);
    }


}
