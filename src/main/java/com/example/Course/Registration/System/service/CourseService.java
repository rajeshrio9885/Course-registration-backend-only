package com.example.Course.Registration.System.service;

import java.util.List;

import com.example.Course.Registration.System.controller.model.Course;
import com.example.Course.Registration.System.controller.model.Enrolled;
import com.example.Course.Registration.System.controllers.Responses;

public interface CourseService {
	List<Course> avaibleCourse();
	Responses addCourse(Course course);
	Responses deleteCourse(Long id);
	Responses updateCourse(Long id ,Course course);
	Responses addStudent(Enrolled enrolled,Long courseId);
	List<Enrolled> getEnrolled();
	List<Enrolled> getEnrolledByTech(String tech);
	List<Enrolled> getBySchoolAndCourse(String schoolName,String courseName);
}
