package com.example.Course.Registration.System.repositry;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.Course.Registration.System.controller.model.Enrolled;

@Repository
public interface EnrolledRep extends JpaRepository<Enrolled, Long>{
	List<Enrolled> findByCourseName(String tech);
	List<Enrolled> findByStudentName(String tech);
	
	@Query(nativeQuery = true,value = "SELECT * FROM enrolled WHERE school_name =:schoolName AND course_name=:courseName")
	List<Enrolled> findBySchoolNameAndCourseName(@Param("schoolName") String schoolName,@Param("courseName") String courseName);
}
