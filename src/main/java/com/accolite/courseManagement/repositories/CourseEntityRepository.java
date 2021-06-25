package com.accolite.courseManagement.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.accolite.courseManagement.entities.CourseEntity;

@Repository
public interface CourseEntityRepository extends JpaRepository<CourseEntity, Long> {
	
	Optional<CourseEntity> findByLocation(String location);
	
//	@Query("SELECT ce.id, ce.description, ce.feedback, ce.lastupdated, ce.location, ce.prerequisite FROM course_entity ce JOIN course_entity_skill on ce.id=course_entity_skill.course_entity_id join skill on course_entity_skill.skill_id=skill.id where skill.name=?1")
//	Object getCourseBySkill(String skill);
	
}
