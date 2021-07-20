package com.accolite.courseManagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.accolite.courseManagement.entities.CourseEntity;
import com.accolite.courseManagement.entities.Participants;
import com.accolite.courseManagement.repositories.ParticipantRepository;
import com.accolite.courseManagement.service.ParticipantService;

@CrossOrigin(origins = "http://localhost:4200")

@RestController
@RequestMapping("/participant")
public class ParticipantController {
	
	@Autowired
	private ParticipantRepository participantRepository;
	
	@Autowired
	private ParticipantService participantService;
	
	@GetMapping()
	public List<Participants> getAllParticipants(){
		return this.participantRepository.findAll();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<HttpStatus> deleteParticipant(@PathVariable("id") Long id){
		try {
			this.participantRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch(Exception e){
			return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
		}
	}
	
	@PostMapping("/save")
	public ResponseEntity<Integer> saveAdmin(@RequestBody Participants participant){
		return new ResponseEntity<>(this.participantService.saveParticipant(participant), HttpStatus.OK);
	}
	
	@PostMapping("/login")
	public ResponseEntity<Integer> loginAdmin(@RequestBody Participants participant){
		return new ResponseEntity<>(this.participantService.loginParticipant(participant), HttpStatus.OK);
	}
	
	@GetMapping("/courses/{username}")
	public List<CourseEntity> getMyCourses(@PathVariable("username") String username){
		Participants participant = participantRepository.findByUsername(username);
		return participant.getCourses();
	}
	
	@GetMapping("/unsubscribe/{username}/{courseId}")
	public Integer unsubscribeCourse(@PathVariable("username") String username, 
			@PathVariable("courseId") Long courseId){
		return this.participantService.unsubscribeCourse(username, courseId);
	}
	
	@GetMapping("/subscribe/{username}/{courseId}")
	public Integer subscribeCourse(@PathVariable("username") String username, 
			@PathVariable("courseId") Long courseId){
		return this.participantService.subscribeCourse(username, courseId);
	}
}
