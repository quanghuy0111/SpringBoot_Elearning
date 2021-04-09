package com.cybersoft.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
@Entity
@Table(name = "courses")
public class Course {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String title;
	private String image;
	
	@Column(name = "lectures_count")
	private int lecturesCount;
	
	@Column(name = "hour_count")
	private int hourCount;
	
	@Column(name = "view_count")
	private int viewCount;
	private double price;
	private int discount;
	@Column(name = "promotion_price")
	private double promotionPrice;
	
	private String description;
	
	private String content;

	
	@Column(name = "last_update")
	@Temporal(TemporalType.DATE)
	private Date lastUpdate;
	
	@ManyToOne
	@JoinColumn(name = "category_id")
	private Category category;
	
	@OneToMany(mappedBy = "course",cascade = CascadeType.ALL)
	private List<UserCourse> userCourses;
	
	@OneToMany(mappedBy = "course",cascade = CascadeType.ALL)
	private List<Video> videoCourses;
	
	@OneToMany(mappedBy = "course",cascade = CascadeType.ALL)
	private List<Target> targetCourses;
	
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

	public int getLeturesCount() {
		return lecturesCount;
	}

	public void setLeturesCount(int leturesCount) {
		this.lecturesCount = leturesCount;
	}

	public int getHourCount() {
		return hourCount;
	}

	public void setHourCount(int hourCount) {
		this.hourCount = hourCount;
	}

	public int getViewCount() {
		return viewCount;
	}

	public void setViewCount(int viewCount) {
		this.viewCount = viewCount;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	
	public Date getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public List<UserCourse> getUserCourses() {
		return userCourses;
	}

	public void setUserCourses(List<UserCourse> userCourses) {
		this.userCourses = userCourses;
	}
	
	public Course(int id, String title, String image, int leturesCount, int hourCount, int viewCount, double price,
			int discount, double promotionPrice, String description, String content, int categoryId, Date lastUpdate,
			Category category) {
		super();
		this.id = id;
		this.title = title;
		this.image = image;
		this.lecturesCount = leturesCount;
		this.hourCount = hourCount;
		this.viewCount = viewCount;
		this.price = price;
		this.discount = discount;
		this.promotionPrice = promotionPrice;
		this.description = description;
		this.content = content;

		this.lastUpdate = lastUpdate;
		this.category = category;
	}

	public Course() {
		// TODO Auto-generated constructor stub
	}
	
	
	

	public Course(int id, String title, String image, int leturesCount, int hourCount, int viewCount, double price,
			int discount, double promotionPrice, String description) {
		super();
		this.id = id;
		this.title = title;
		this.image = image;
		this.lecturesCount = leturesCount;
		this.hourCount = hourCount;
		this.viewCount = viewCount;
		this.price = price;
		this.discount = discount;
		this.promotionPrice = promotionPrice;
		this.description = description;
	}

	@Override
	public String toString() {
		return "Course [id=" + id + ", title=" + title + ", image=" + image + ", leturesCount=" + lecturesCount
				+ ", hourCount=" + hourCount + ", viewCount=" + viewCount + ", price=" + price + ", discount="
				+ discount + ", promotionPrice=" + promotionPrice + ", description=" + description + ", content="
				+ content + ", categoryId=" + ", lastUpdate=" + lastUpdate + ", category=" + category
				+ ", userCourses=" + userCourses + "]";
	}
	
	
	

	

	
	
	
}
