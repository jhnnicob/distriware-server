package com.wkt.distriware.model;

public class User {

	private String userId;
	private String username;
	private String password;
	private String userType;
	
	private String token;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
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

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public User(String userId, String username, String password, String userType) {
		super();
		this.userId = userId;
		this.username = username;
		this.password = password;
		this.userType = userType;
	}

}
