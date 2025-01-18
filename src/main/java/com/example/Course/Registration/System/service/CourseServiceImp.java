package com.example.Course.Registration.System.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.Course.Registration.System.controller.model.Course;
import com.example.Course.Registration.System.controller.model.Enrolled;
import com.example.Course.Registration.System.controllers.Responses;
import com.example.Course.Registration.System.repositry.CourseRep;
import com.example.Course.Registration.System.repositry.EnrolledRep;




@Service
public class CourseServiceImp implements CourseService {
	
	@Autowired
	CourseRep courseRep;
	
	@Autowired
	EnrolledRep enrolledRep;

	Responses responses;
	
	@Override
	public List<Course> avaibleCourse() {
		return courseRep.findAll();
	}

	@Override
	public Responses addCourse(Course course) {
		courseRep.save(course);
		Responses r1 = new Responses();
		r1.setMessage("created successfully");
		return r1;
	}

	@Override
	public Responses deleteCourse(Long id) {
		Responses r1 = new Responses();

		Optional<Course> course = courseRep.findById(id);
		
		Course isAvailable = course.stream().filter(c->c.getCourseId().equals(id)).findFirst().orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"Course not found"));
		
		courseRep.delete(isAvailable);
		r1.setMessage("Course deleted successfully "+isAvailable.getCourseName());
		return r1;
	
	}
	
	@Override
	public Responses updateCourse(Long id,Course course) {
		Responses r1 = new Responses();
		Optional<Course> courses = courseRep.findById(id);
		
		Course isAvailable = courses.stream().filter(c->c.getCourseId().equals(id)).findFirst().orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"Not found"));
		
		isAvailable.setCourseName(course.getCourseName());
		isAvailable.setTrainer(course.getTrainer());
		isAvailable.setDurationInWeeks(course.getDurationInWeeks());
		isAvailable.setSeatAvailable(course.getSeatAvailable());
		courseRep.save(isAvailable);
		r1.setMessage("course updated sucessfully");
		return r1;
	}

	@Override
	public Responses addStudent(Enrolled enrolled,Long courseId) {
		Responses r1 = new Responses();
		Optional<Course> selectedCourse = courseRep.findById(courseId);
		Course course = selectedCourse.stream().filter(c->c.getCourseId().equals(courseId)).findFirst().orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"Course not found"));

		if(course.getSeatAvailable() != 0) {
			course.setSeatAvailable(course.getSeatAvailable()-1);
			courseRep.save(course);
			enrolledRep.save(enrolled);
			r1.setMessage("registed successfully");
			return r1;
		}else {
			r1.setMessage("There is no sit available");
			return r1;
		}
		
	}

	@Override
	public List<Enrolled> getEnrolled() {
		return enrolledRep.findAll();
	}

	@Override
	public List<Enrolled> getEnrolledByTech(String tech) {
		return enrolledRep.findByStudentName(tech);
	}

	@Override
	public List<Enrolled> getBySchoolAndCourse(String schoolName, String courseName) {
		// TODO Auto-generated method stub
		return enrolledRep.findBySchoolNameAndCourseName(schoolName, courseName);
	}
	
}
