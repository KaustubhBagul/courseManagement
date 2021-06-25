package com.accolite.courseManagement.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class CourseEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String feedback;
	private String description;
	private String lastupdated;
	private String location;
	private String prerequisite;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Skill> skill;
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Creator> creator;
	
	public CourseEntity() {
		
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFeedback() {
		return feedback;
	}
	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getLastupdated() {
		return lastupdated;
	}
	public void setLastupdated(String lastupdated) {
		this.lastupdated = lastupdated;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getPrerequisite() {
		return prerequisite;
	}
	public void setPrerequisite(String prerequisite) {
		this.prerequisite = prerequisite;
	}
	public List<Skill> getSkill() {
		return skill;
	}
	public void setSkill(List<Skill> skill) {
		this.skill = skill;
	}
	public List<Creator> getCreator() {
		return creator;
	}
	public void setCreator(List<Creator> creator) {
		this.creator = creator;
	}
	
	@Override
	public String toString() {
		return "CourseEntity [id=" + id + ", feedback=" + feedback + ", description=" + description + ", lastupdated="
				+ lastupdated + ", location=" + location + ", prerequisite=" + prerequisite + "]";
	}
	
	
}
