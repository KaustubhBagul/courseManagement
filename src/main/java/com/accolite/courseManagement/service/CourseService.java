package com.accolite.courseManagement.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.mail.MailException;
import org.springframework.stereotype.Service;

import com.accolite.courseManagement.entities.CourseEntity;
import com.accolite.courseManagement.entities.Creator;
import com.accolite.courseManagement.entities.Participants;
import com.accolite.courseManagement.entities.Skill;
import com.accolite.courseManagement.exception.NoContentException;
import com.accolite.courseManagement.models.Course;
import com.accolite.courseManagement.models.Mail;
//import com.accolite.courseManagement.models.Mail;
import com.accolite.courseManagement.repositories.CourseEntityRepository;
import com.accolite.courseManagement.repositories.CreatorRepository;
import com.accolite.courseManagement.repositories.ParticipantRepository;
import com.accolite.courseManagement.repositories.SkillRepository;

@Service
public class CourseService {
	
	@Autowired
	private CourseEntityRepository courseEntityRepository;
	
	@Autowired
	private ParticipantRepository participantRepository;
	
	@Autowired
	private CreatorRepository creatorRepository;
	
	@Autowired
	private SkillRepository skillRepository;
	
	@Autowired
	private Mail myMail;
	
	public Course createCourse(Course course) {
		CourseEntity courseEntity = this.courseEntityRepository.save(mapObjectToEntity(course));
		return mapEntityToObject(courseEntity);
//		Mail myMail = new Mail();
//		myMail.sendSimpleMessage("kvkaustubhbagul1234@gmail.com", "test", "this is just a test");
	}
	
	public CourseEntity mapObjectToEntity(Course course) {
		CourseEntity entity = new CourseEntity();
		
		entity.setId(course.getId());
		entity.setDescription(course.getDescription());
		entity.setPrerequisite(course.getPrerequisite());
		
		entity.setLastupdated(course.getLastupdated());
		
		entity.setFeedback(course.getFeedback());
		entity.setSkill(addSkill(course));
		entity.setCreator(addCreator(course));
		entity.setLocation(course.getLocation());
		
		return entity;
	}
	
	private List<Skill> addSkill(Course course) {
		List<Skill> skillList = new ArrayList<>();
		int n = course.getSkills().size();
		long skillId = 0;
		String name = null;

		for (int i = 0; i < n; i++) {
			skillId = course.getSkills().get(i).getId();
			name = course.getSkills().get(i).getName();

			Skill skill = new Skill(skillId, name);
			//skillRepository.save(skill);
			skillList.add(skill);

		}

		return skillList;
	}
	
	private List<Skill> addSkill(CourseEntity course) {
		List<Skill> skillList = new ArrayList<>();
		int n = course.getSkill().size();
		long skillId = 0;
		String name = null;

		for (int i = 0; i < n; i++) {
			skillId = course.getSkill().get(i).getId();
			name = course.getSkill().get(i).getName();

			Skill skill = new Skill(skillId, name);
			
			skillList.add(skill);

		}

		return skillList;

	}
	
	private List<Creator> addCreator(Course course) {		
		List<Creator> creatorList = new ArrayList<>();
		int n = course.getCreator().size();
		long creatorId = 0;
		String name = null;

		for (int i = 0; i < n; i++) {
			creatorId = course.getCreator().get(i).getId();
			name = course.getCreator().get(i).getName();

			Creator creator = new Creator(creatorId, name);
			
			creatorList.add(creator);

		}

		return creatorList;

	}
	private List<Creator> addCreator(CourseEntity course) {
		
		List<Creator> creatorList = new ArrayList<>();
		int n = course.getCreator().size();
		long creatorId = 0;
		String name = null;

		for (int i = 0; i < n; i++) {
			creatorId = course.getCreator().get(i).getId();
			name = course.getCreator().get(i).getName();

			Creator creator = new Creator(creatorId, name);
			
			creatorList.add(creator);

		}

		return creatorList;

	}
	
	public Course mapEntityToObject(CourseEntity entity) {

		Course course = new Course();
		course.setId(entity.getId());
		course.setDescription(entity.getDescription());
		course.setPrerequisite(entity.getPrerequisite());
		course.setLastupdated(entity.getLastupdated());
		course.setFeedback(entity.getFeedback());
		course.setSkills(addSkill(entity));
		course.setCreator(addCreator(entity));
		course.setLocation(entity.getLocation());

		return course;
	}
	
	public Course getCourseById(Long id) throws NoContentException {

		Optional<CourseEntity> entity = courseEntityRepository.findById(id);
		if (!entity.isPresent()) {
			throw new NoContentException(HttpStatus.NO_CONTENT);
		}
		return mapEntityToObject(entity.get());

	}
	
	public Course getCourseByLocation(String loc) throws NoContentException {

		Optional<CourseEntity> entity = courseEntityRepository.findByLocation(loc);
		if (!entity.isPresent()) {
			throw new NoContentException(HttpStatus.NO_CONTENT);
		}
		return mapEntityToObject(entity.get());
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
	
	public Course updateCourse(Course course) {
		CourseEntity courseEntity = this.courseEntityRepository.save(mapObjectToEntity(course));
		return mapEntityToObject(courseEntity);
	}
}
