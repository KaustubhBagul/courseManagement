package com.accolite.courseManagement.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Skill {
	
	@Id
	private Long id;
	private String name;
	
	public Skill() {
		
	}
	
	public Skill(long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Skill [id=" + id + ", name=" + name + "]";
	}
}
