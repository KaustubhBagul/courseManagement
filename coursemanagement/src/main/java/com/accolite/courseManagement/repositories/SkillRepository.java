package com.accolite.courseManagement.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.accolite.courseManagement.entities.Skill;

@Repository
public interface SkillRepository extends JpaRepository<Skill, Long> {
	
	Skill findByName(String name);
}
