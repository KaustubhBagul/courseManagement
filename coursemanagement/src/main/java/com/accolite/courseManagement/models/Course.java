package com.accolite.courseManagement.models;

import java.util.List;

import com.accolite.courseManagement.entities.Creator;
import com.accolite.courseManagement.entities.Skill;

public class Course {
	
	private Long id;
	private String title;
	private String description;
	private String prerequisite;
	private String lastupdated;
	private String feedback;
	private String location;
	private List<Skill> skills;
	private List<Creator> creator;
	
	public Course() {
		
	}
	
	public Course(Long id, String title, String description, String prerequisite, String lastupdated, String feedback,
			String location, List<Skill> skills, List<Creator> creator) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.prerequisite = prerequisite;
		this.lastupdated = lastupdated;
		this.feedback = feedback;
		this.location = location;
		this.skills = skills;
		this.creator = creator;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getPrerequisite() {
		return prerequisite;
	}
	public void setPrerequisite(String prerequisite) {
		this.prerequisite = prerequisite;
	}
	public String getLastupdated() {
		return lastupdated;
	}
	public void setLastupdated(String lastupdated) {
		this.lastupdated = lastupdated;
	}
	public String getFeedback() {
		return feedback;
	}
	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}
	public List<Skill> getSkills() {
		return skills;
	}
	public void setSkills(List<Skill> skills) {
		this.skills = skills;
	}
	public List<Creator> getCreator() {
		return creator;
	}
	public void setCreator(List<Creator> creator) {
		this.creator = creator;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	
	
}
