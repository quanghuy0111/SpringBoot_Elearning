package com.cybersoft.dto;

import com.cybersoft.entity.Course;
import com.cybersoft.entity.User;

public class UserCourseDto {
	private int id;
	private User user;
	private Course course;
	private int roleId;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Course getCourse() {
		return course;
	}
	public void setCourse(Course course) {
		this.course = course;
	}
	public int getRoleId() {
		return roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	public UserCourseDto(int id, User user, Course course, int roleId) {
		super();
		this.id = id;
		this.user = user;
		this.course = course;
		this.roleId = roleId;
	}
	
	
	
	
	
}
