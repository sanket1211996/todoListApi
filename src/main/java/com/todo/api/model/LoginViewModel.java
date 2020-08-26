package com.todo.api.model;

public class LoginViewModel {
	
	String username;
	String password;
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
	
	public LoginViewModel() {}
	
	public LoginViewModel(String username, String password) {
		this.username = username;
		this.password = password;
	}
	
	@Override
	public String toString() {
		return "LoginViewModel [username=" + username + ", password=" + password + "]";
	}
	
	

}
