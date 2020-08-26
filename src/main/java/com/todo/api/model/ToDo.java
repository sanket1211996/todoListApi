package com.todo.api.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ToDo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Long id;
	Long userID;
	String title;
	String description;
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
	
	public ToDo() {}
	
	public ToDo(Long userID,String title, String description) {
		this.userID = userID;
		this.title = title;
		this.description = description;
	}
	

	public Long getUserID() {
		return userID;
	}
	public void setUserID(Long userID) {
		this.userID = userID;
	}
	@Override
	public String toString() {
		return "ToDo [id=" + id + ", userID=" + userID + ", title=" + title + ", description=" + description + "]";
	}

}
