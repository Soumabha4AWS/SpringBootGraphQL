package com.springboot.SampleGraphQlAPIWithMySqlDatabase.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.SampleGraphQlAPIWithMySqlDatabase.entity.CourseEntity;


public interface CourseRepository extends JpaRepository<CourseEntity,String> {
    CourseEntity findByCourseName(String courseName);
}

