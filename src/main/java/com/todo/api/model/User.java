package com.todo.api.model;

import java.util.Arrays;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Long id;
	
	@Column(name="username", unique=true)
	String username;
	String password;
	String email;
	String permissions;
	String roles;
	boolean isActive = true;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPermissions() {
		return permissions;
	}
	public void setPermissions(String permissions) {
		this.permissions = permissions;
	}
	public String getRoles() {
		return roles;
	}
	public void setRoles(String roles) {
		this.roles = roles;
	}
	public boolean isActive() {
		return isActive;
	}
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	
	public List<String> getPersmissionList() {
		return Arrays.asList(this.permissions.split(","));
	}
	
	public List<String> getRoleList() {
		return Arrays.asList(this.roles.split(","));
	}

	public User() {}
	
	public User(String username, String password, String permissions, String roles, String email) {
		this.username = username;
		this.password = password;
		this.permissions = permissions;
		this.roles = roles;
		this.isActive = true;
		this.email = email;
	}
	
	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", permissions=" + permissions
				+ ", roles=" + roles + ", isActive=" + isActive + "]";
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	

}
