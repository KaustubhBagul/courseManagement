package com.accolite.courseManagement.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.accolite.courseManagement.entities.CourseEntity;
import com.accolite.courseManagement.entities.Skill;

@Repository
public interface CourseEntityRepository extends JpaRepository<CourseEntity, Long> {
	
	List<CourseEntity> findAllByLocation(String location);
	
	List<CourseEntity> findAllBySkill(Skill skill); 
	
}
