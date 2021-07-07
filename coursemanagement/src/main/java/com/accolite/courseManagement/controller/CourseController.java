package com.accolite.courseManagement.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.accolite.courseManagement.entities.CourseEntity;
import com.accolite.courseManagement.exception.NoContentException;
import com.accolite.courseManagement.models.Course;
import com.accolite.courseManagement.repositories.CourseEntityRepository;
import com.accolite.courseManagement.service.CourseService;

@CrossOrigin(origins = "http://localhost:4200")

@RestController
@RequestMapping("/course/")
public class CourseController {

	@Autowired
	private CourseService courseService;
	
	@Autowired
	private CourseEntityRepository courseEntityRepository;

	@GetMapping("/")
	public List<CourseEntity> getAllCourses(){
		List<CourseEntity> coursesList = courseEntityRepository.findAll();
		return coursesList;
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Course> getCourseById(@PathVariable("id") Long id) {
		Course courseData = null;
		try {
			courseData = courseService.getCourseById(id);
		} catch (NoContentException e) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}

		return new ResponseEntity<>(courseData, HttpStatus.OK);
	}
	
	@GetMapping("/loc/{loc}")
	public ResponseEntity<List<Course>> getCourseByLocation(@PathVariable("loc") String loc) {
		List<Course> courseData = null;
		try {
			courseData = courseService.getCourseByLocation(loc);
		} catch (NoContentException e) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}

		return new ResponseEntity<>(courseData, HttpStatus.OK);
	}
		
	@PostMapping("/")
	public ResponseEntity<Course> createCourse(@RequestBody Course course){
		return new ResponseEntity<>(courseService.createCourse(course), HttpStatus.OK);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Course> updateCourse(@PathVariable("id") Long id, @RequestBody Course course){
		Optional<CourseEntity> courseFound = courseEntityRepository.findById(id);
		if(courseFound.isPresent()) {
			return new ResponseEntity<>(courseService.updateCourse(course), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<HttpStatus> deleteCourse(@PathVariable("id") Long id){
		try {
			this.courseEntityRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch(Exception e){
			return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
		}
	}
	
	@GetMapping("/skill/{skill}")
	public ResponseEntity<List<Course>> getCourseBySkill(@PathVariable("skill") String skill) {
		List<Course> courses = null;
		courses = courseService.getCourseBySkill(skill);
		return new ResponseEntity<>(courses, HttpStatus.OK);
	}
}
