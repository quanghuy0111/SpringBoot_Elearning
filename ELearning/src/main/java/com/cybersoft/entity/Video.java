package com.cybersoft.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "videos")
public class Video {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String title;
	private String url;
	private String image;
	
	
	
	public int getCourseId() {
		return courseId;
	}

	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public int getTimeCount() {
		return timeCount;
	}

	public void setTimeCount(int timeCount) {
		this.timeCount = timeCount;
	}
	
	

	public Video(String title, String url, String image, int timeCount, int courseId) {
		super();
		this.title = title;
		this.url = url;
		this.image = image;
		this.timeCount = timeCount;
		this.courseId = courseId;
	}
	
	public Video() {
		
	}


	@Column(name = "time_count")
	private int timeCount;
	
	@Column(name = "course_id")
	private int courseId;
	
	@ManyToOne
	@JoinColumn(name = "course_id",insertable = false, updatable = false)
	private Course course;
}
