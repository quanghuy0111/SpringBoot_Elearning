package com.cybersoft.dto;

import javax.validation.constraints.NotEmpty;

public class CategoryDto {
	private int id;
	@NotEmpty(message = "Vui lòng không được để trống title")
	private String title;
	@NotEmpty(message = "Vui lòng không để trống icon")
	private String icon;
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
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}

	public CategoryDto(int id, String title, String icon) {
		super();
		this.id = id;
		this.title = title;
		this.icon = icon;
	}
	public CategoryDto() {
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "CategoryDto [id=" + id + ", title=" + title + ", icon=" + icon + "]";
	}


}
