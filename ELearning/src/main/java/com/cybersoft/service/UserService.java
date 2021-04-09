package com.cybersoft.service;

import java.util.List;

import com.cybersoft.dto.UserDto;

public interface UserService {
	void insert(UserDto dto);
	List<UserDto> getAll();
	UserDto getById(int id);
	void update(UserDto dto);
	void delete(int id);
	List<UserDto> getStudentOfCourse(int id);
	UserDto getTheLastUser();
	UserDto getByEmail(String email);
	void insertUserRegister(UserDto dto);
	void updateProfile(UserDto dto);
}
