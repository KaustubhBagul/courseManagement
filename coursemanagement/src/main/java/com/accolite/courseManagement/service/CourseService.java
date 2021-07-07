package com.accolite.courseManagement.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.accolite.courseManagement.entities.CourseEntity;
import com.accolite.courseManagement.entities.Creator;
import com.accolite.courseManagement.entities.Skill;
import com.accolite.courseManagement.exception.NoContentException;
import com.accolite.courseManagement.models.Course;
//import com.accolite.courseManagement.models.Mail;
import com.accolite.courseManagement.repositories.CourseEntityRepository;
import com.accolite.courseManagement.repositories.CreatorRepository;
import com.accolite.courseManagement.repositories.SkillRepository;

@Service
public class CourseService {
	
	@Autowired
	private CourseEntityRepository courseEntityRepository;
	
	@Autowired
	private CreatorRepository creatorRepository;
	
	@Autowired
	private SkillRepository skillRepository;
	
	@Autowired
	private ParticipantService participantService;
	
	private List<Skill> addSkill(Course course) {
		List<Skill> skillList = new ArrayList<>();
		int n = course.getSkills().size();
		String name = null;

		for (int i = 0; i < n; i++) {
			name = course.getSkills().get(i).getName();
			Skill skill = this.getSkillByName(name);
			if(skill == null) {
				skill = new Skill(name);
			}
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
		String name = null;

		for (int i = 0; i < n; i++) {
			name = course.getCreator().get(i).getName();
			Creator creator = this.getCreatorByName(name);
			if(creator == null) {
				creator = new Creator(name);
			}
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
	
	public Course createCourse(Course course) {
		CourseEntity courseEntity = this.courseEntityRepository.save(mapObjectToEntity(course));
		participantService.sendMail(course);
		return mapEntityToObject(courseEntity);
	}
	
	public CourseEntity mapObjectToEntity(Course course) {
		CourseEntity entity = new CourseEntity();
		
		entity.setId(course.getId());
		entity.setTitle(course.getTitle());
		entity.setDescription(course.getDescription());
		entity.setPrerequisite(course.getPrerequisite());
		entity.setLastupdated(course.getLastupdated());
		entity.setFeedback(course.getFeedback());
		entity.setSkill(addSkill(course));
		entity.setCreator(addCreator(course));
		entity.setLocation(course.getLocation());
		
		return entity;
	}
	
	public Course mapEntityToObject(CourseEntity entity) {

		Course course = new Course();
		course.setId(entity.getId());
		course.setTitle(entity.getTitle());
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
	
	public List<Course> getCourseByLocation(String loc) throws NoContentException {

		List<CourseEntity> entity = courseEntityRepository.findAllByLocation(loc);
		if (entity == null) {
			throw new NoContentException(HttpStatus.NO_CONTENT);
		}
		List<Course> course = new ArrayList<>();
		for(CourseEntity courses: entity) {
			course.add(mapEntityToObject(courses));
		}
		return course;
	}
	
	
	public Course updateCourse(Course course) {
		CourseEntity courseEntity = this.courseEntityRepository.save(mapObjectToEntity(course));
		return mapEntityToObject(courseEntity);
	}
	
	public List<Course> getCourseBySkill(String skill) {
		Skill skillEntity = this.skillRepository.findByName(skill);
		List<CourseEntity> courseEntity = this.courseEntityRepository.findAllBySkill(skillEntity);
		List<Course> list = new ArrayList<>();
		for(CourseEntity course: courseEntity)
			list.add(mapEntityToObject(course));
		return list;
	}
	
	private Skill getSkillByName(String name) {
		return this.skillRepository.findByName(name);
	}
	
	private Creator getCreatorByName(String name) {
		return this.creatorRepository.findByName(name);
	}
}
