package com.accolite.courseManagement.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.accolite.courseManagement.entities.Skill;

public interface SkillRepository extends JpaRepository<Skill, Long> {

}
