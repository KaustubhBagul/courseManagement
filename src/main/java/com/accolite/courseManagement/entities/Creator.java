package com.accolite.courseManagement.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Creator {
	
	@Id
	private Long id;
	private String name;
	
	public Creator() {
		
	}
	
	public Creator(long id, String name) {
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
		return "Creator [id=" + id + ", name=" + name + "]";
	}	
}
