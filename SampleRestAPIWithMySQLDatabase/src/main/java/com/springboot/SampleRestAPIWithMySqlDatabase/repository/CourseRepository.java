package com.springboot.SampleRestAPIWithMySqlDatabase.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.SampleRestAPIWithMySqlDatabase.entity.CourseEntity;


public interface CourseRepository extends JpaRepository<CourseEntity,String> {
    CourseEntity findByCourseName(String courseName);
}

