package com.accolite.courseManagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.stereotype.Service;

import com.accolite.courseManagement.entities.Participants;
import com.accolite.courseManagement.models.Course;
import com.accolite.courseManagement.models.Mail;
import com.accolite.courseManagement.repositories.ParticipantRepository;

@Service
public class ParticipantService {

	@Autowired
	private ParticipantRepository participantRepository;

	@Autowired
	private Mail myMail;
	
	public Participants addParticipant(Participants participant) {
		Participants added = this.participantRepository.save(participant);
		return added;
	}
	
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
}
