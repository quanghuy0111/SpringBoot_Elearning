package com.cybersoft.dto;

public class VideoDto {
	private int id;
	private String title;
	private String url;
	private String image;
	private int timeCount;
	private int courseId;
	private String courseDes;
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
	public int getCourseId() {
		return courseId;
	}
	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}
	public String getCourseDes() {
		return courseDes;
	}
	public void setCourseDes(String courseDes) {
		this.courseDes = courseDes;
	}
	public VideoDto(int id, String title, String url, String image, int timeCount, String courseDes) {
		super();
		this.id = id;
		this.title = title;
		this.url = url;
		this.image = image;
		this.timeCount = timeCount;
		this.courseDes = courseDes;
	}
	public VideoDto() {
		
	}

	public VideoDto(int id, String title, String url, String image, int timeCount, int courseId) {
		
		this.id = id;
		this.title = title;
		this.url = url;
		this.image = image;
		this.timeCount = timeCount;
		this.courseId = courseId;
	}
	
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	
}
