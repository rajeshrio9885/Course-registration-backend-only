package com.example.Course.Registration.System.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.Course.Registration.System.controller.model.Course;
import com.example.Course.Registration.System.controller.model.Enrolled;
import com.example.Course.Registration.System.service.CourseService;



@RestController
public class CourseController {
	
	@Autowired
	CourseService courseService;
	
	Responses responsesAll = new Responses();
	
	@GetMapping("/courses")
	public List<Course> getCourses(){
		return courseService.avaibleCourse();
	}
	
	@PostMapping("/courses")
	public ResponseEntity<Responses>  addCourse(@RequestBody Course course) {
		 Responses responses =  courseService.addCourse(course);
		 return new ResponseEntity<>(responses,HttpStatus.CREATED);
	}
	
	@DeleteMapping("/courses/{id}")
	public ResponseEntity<Responses> deleteCourse(@PathVariable Long id){
		
		
		try {
			Responses responses =courseService.deleteCourse(id);
			return new ResponseEntity<>(responses,HttpStatus.OK);
		} catch (ResponseStatusException e) {
			responsesAll.setMessage(e.getReason());
			return new ResponseEntity<>(responsesAll,e.getStatusCode());
		}
		 
	}
	
	@PutMapping("/courses/{id}")
	public ResponseEntity<Responses> updateCourse(@PathVariable Long id,@RequestBody Course course){
		try {
			Responses responses = courseService.updateCourse(id ,course);
			return new ResponseEntity<Responses>(responses,HttpStatus.OK);
		} catch (ResponseStatusException e) {
			responsesAll.setMessage(e.getReason());
			return new ResponseEntity<Responses>(responsesAll,HttpStatus.NOT_FOUND);
		}
		
	}
	
	@PostMapping("/enrolled/{courseId}")
	public ResponseEntity<Responses> createEnrolled(@RequestBody Enrolled enrolled,@PathVariable Long courseId){
		try {
			Responses responses = courseService.addStudent(enrolled,courseId);
			return new ResponseEntity<Responses>(responses,HttpStatus.CREATED);
		} catch (ResponseStatusException e) {
			responsesAll.setMessage(e.getReason());
			return new ResponseEntity<Responses>(responsesAll,HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/enrolled")
	public List<Enrolled> getEnrolled(){
		return courseService.getEnrolled();
	}
	
	@GetMapping("/enrolled/{tech}")
	public List<Enrolled> getEnrolledByTech(@PathVariable String tech){
		return courseService.getEnrolledByTech(tech);
	}
	
	@PostMapping("/enrolled/two")
	public List<Enrolled> getBySchoolAndCourse(@Param("schoolName") String schoolName,@Param("courseName") String courseName){
		return courseService.getBySchoolAndCourse(schoolName,courseName);
	}
}
