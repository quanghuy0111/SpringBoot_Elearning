package com.cybersoft.dto;

import java.util.List;

import javax.validation.constraints.NotEmpty;

import com.cybersoft.entity.UserCourse;

public class UserDto {
	
	private int id;
	@NotEmpty(message = "Vui lòng nhập tên")
	private String fullname;
	@NotEmpty(message = "Vui lòng nhập email")
	private String email;
	@NotEmpty(message = "Vui lòng nhập password")
	private String password;
	@NotEmpty(message = "Vui lòng nhập avatar")
	private String avatar;
	
	private String address;
	private String phone;
	
	private int roleId;
	private String roleDesc;
	private List<UserCourse> userCourses;
	
	public UserDto() {}
	
	public UserDto(int id, String fullname, String email, String roleDesc) {

		this.id = id;
		this.fullname = fullname;
		this.email = email;
		this.roleDesc = roleDesc;
	}
	
	public UserDto(int id, String fullname, String email, String password, String avatar, int roleId) {

		this.id = id;
		this.fullname = fullname;
		this.email = email;
		this.password = password;
		this.avatar = avatar;
		this.roleId = roleId;
	}
	
	
	
	

	public UserDto(int id, @NotEmpty(message = "Vui lòng nhập tên") String fullname,
			@NotEmpty(message = "Vui lòng nhập email") String email,
			@NotEmpty(message = "Vui lòng nhập password") String password,
			@NotEmpty(message = "Vui lòng nhập avatar") String avatar, String address, String phone, int roleId,
			String roleDesc, List<UserCourse> userCourses) {
		super();
		this.id = id;
		this.fullname = fullname;
		this.email = email;
		this.password = password;
		this.avatar = avatar;
		this.address = address;
		this.phone = phone;
		this.roleId = roleId;
		this.roleDesc = roleDesc;
		this.userCourses = userCourses;
	}

	public UserDto(int id, @NotEmpty(message = "Vui lòng nhập tên") String fullname,
			@NotEmpty(message = "Vui lòng nhập email") String email,
			@NotEmpty(message = "Vui lòng nhập password") String password,
			@NotEmpty(message = "Vui lòng nhập avatar") String avatar, int roleId,
			List<UserCourse> userCourses) {
		super();
		this.id = id;
		this.fullname = fullname;
		this.email = email;
		this.password = password;
		this.avatar = avatar;
		this.roleId = roleId;

		this.userCourses = userCourses;
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
	public String getFullname() {
		return fullname;
	}
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getRoleDesc() {
		return roleDesc;
	}
	public void setRoleDesc(String roleDesc) {
		this.roleDesc = roleDesc;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	
	
	

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public UserDto(int id, @NotEmpty(message = "Vui lòng nhập tên") String fullname,
			@NotEmpty(message = "Vui lòng nhập email") String email) {
		super();
		this.id = id;
		this.fullname = fullname;
		this.email = email;
	}
	
	

	@Override
	public String toString() {
		return "UserDto [id=" + id + ", fullname=" + fullname + ", email=" + email + ", password=" + password
				+ ", avatar=" + avatar + ", roleId=" + roleId + ", roleDesc=" + roleDesc + ", userCourses="
				+ userCourses + "]";
	}


	
}
