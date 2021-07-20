package com.accolite.courseManagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.stereotype.Service;

import com.accolite.courseManagement.entities.CourseEntity;
import com.accolite.courseManagement.entities.Participants;
import com.accolite.courseManagement.models.Course;
import com.accolite.courseManagement.models.Mail;
import com.accolite.courseManagement.repositories.CourseEntityRepository;
import com.accolite.courseManagement.repositories.ParticipantRepository;

@Service
public class ParticipantService {

	@Autowired
	private ParticipantRepository participantRepository;

	@Autowired
	private CourseEntityRepository courseEntityRepository;

	@Autowired
	private Mail myMail;
	
	public void sendMail(Course course) {
		List<Participants> participantList = participantRepository.findAll();
		for(Participants participants: participantList) {
			try {
				myMail.sendSimpleMessage(participants.getEmail(), "New Course Added", course.getDescription());
			} catch(MailException e){
				System.err.println(e.getMessage());
			}
		}
	}
	
	public Participants getParticipantByEmail(String email) {

		Participants participant = participantRepository.findByEmail(email);
		return participant;
	}
	
	public Participants getParticipantByUsername(String username) {

		Participants participant = participantRepository.findByUsername(username);
		return participant;
	}
	
	public int saveParticipant(Participants participant){
		if(this.getParticipantByEmail(participant.getEmail()) != null)
			return 1;
		if(this.getParticipantByUsername(participant.getUsername()) != null)
			return 2;
		this.participantRepository.save(participant);
		return 0;
	}
	
	public int loginParticipant(Participants participant) {
		Participants respParticipant = participantRepository.findByUsername(participant.getUsername());
		if(respParticipant == null || !(respParticipant.getPassword().equals(participant.getPassword()))) {
			System.out.println("entered");
			return -1;
		}
		return 1;
	}
	
	public Integer subscribeCourse(String username, Long courseId) {
		Participants participant = participantRepository.findByUsername(username);
		List<CourseEntity> course = participant.getCourses();
		CourseEntity foundCourse = courseEntityRepository.getById(courseId);
		course.add(foundCourse);
		this.participantRepository.save(participant);
		return 1;
	}
	
	public Integer unsubscribeCourse(String username, Long courseId) {
		Participants participant = participantRepository.findByUsername(username);
		List<CourseEntity> course = participant.getCourses();
		CourseEntity foundCourse = courseEntityRepository.getById(courseId);
		course.remove(foundCourse);
		this.participantRepository.save(participant);
		return 1;
	}
}
