package com.example.Course.Registration.System.repositry;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Course.Registration.System.controller.model.Course;

@Repository
public interface CourseRep extends JpaRepository<Course, Long> {

}
