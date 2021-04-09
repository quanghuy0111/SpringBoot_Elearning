package com.cybersoft.dto;
import java.util.List;

import com.cybersoft.entity.Category;
import com.cybersoft.entity.UserCourse;
public class CourseDto {
	private int id;
	private String title;
	private String image;
	private int lecturesCount;
	private double price;
	private int hourCount;
	private Category category;
	
	private String content;
	private String description;
	private int discount;
	private double promotionPrice;
	private int viewCount;
	private List<UserCourse> userCourses;
	
	
	
	public int getLecturesCount() {
		return lecturesCount;
	}
	public void setLecturesCount(int lecturesCount) {
		this.lecturesCount = lecturesCount;
	}
	public List<UserCourse> getUserCourses() {
		return userCourses;
	}
	public void setUserCourses(List<UserCourse> userCourses) {
		this.userCourses = userCourses;
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
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public int getLectureCount() {
		return lecturesCount;
	}
	public void setLectureCount(int lectureCount) {
		this.lecturesCount = lectureCount;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	
	
	
	public int getHourCount() {
		return hourCount;
	}
	public void setHourCount(int hourCount) {
		this.hourCount = hourCount;
	}
	
	
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	
	
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getDiscount() {
		return discount;
	}
	public void setDiscount(int discount) {
		this.discount = discount;
	}
	
	
	
	public double getPromotionPrice() {
		return promotionPrice;
	}
	public void setPromotionPrice(double promotionPrice) {
		this.promotionPrice = promotionPrice;
	}
	public int getViewCount() {
		return viewCount;
	}
	public void setViewCount(int viewCount) {
		this.viewCount = viewCount;
	}
	public CourseDto(int id, String title, String image, int lecturesCount, double price) {
		super();
		this.id = id;
		this.title = title;
		this.image = image;
		this.lecturesCount = lecturesCount;
		this.price = price;
	}
	public CourseDto() {
		super();
	}
	public CourseDto(int id, String title, String image, double price) {
		super();
		this.id = id;
		this.title = title;
		this.image = image;
		this.price = price;
	}
	public CourseDto(int id, String title, String image, int lecturesCount, double price, int hourCount,
			String description, int discount, double promotionPrice, int viewCount) {
		super();
		this.id = id;
		this.title = title;
		this.image = image;
		this.lecturesCount = lecturesCount;
		this.price = price;
		this.hourCount = hourCount;
		this.description = description;
		this.discount = discount;
		this.promotionPrice = promotionPrice;
		this.viewCount = viewCount;
	}

	public CourseDto(int id, String title, String image, int lecturesCount, double price, int hourCount,
			String description, int discount, double promotionPrice, int viewCount,String content) {
		super();
		this.id = id;
		this.title = title;
		this.image = image;
		this.lecturesCount = lecturesCount;
		this.price = price;
		this.hourCount = hourCount;
		this.description = description;
		this.discount = discount;
		this.promotionPrice = promotionPrice;
		this.viewCount = viewCount;
		this.content = content;
	}
	
	
	
	
	
	
}
