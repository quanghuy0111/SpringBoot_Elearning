package com.cybersoft.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "user_courses")
public class UserCourse implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	
	
	
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "user_id")
	private User user;
	
	
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "course_id")
	private Course course;
	
	@Column(name = "role_id")
	private int roleId;

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
	
	
	

	

	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public UserCourse() {
		super();
	}

	
	public UserCourse(int id, User user, Course course, int roleId) {
		super();
		this.id = id;
		this.user = user;
		this.course = course;
		this.roleId = roleId;
	}

	@Override
	public String toString() {
		return "UserCourse [user=" + user + ", course=" + course + ", roleId=" + roleId + "]";
	}
	
	
	
}
