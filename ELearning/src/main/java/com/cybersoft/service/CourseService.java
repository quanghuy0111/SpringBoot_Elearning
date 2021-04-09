package com.cybersoft.service;

import java.util.List;

import com.cybersoft.dto.CourseDto;
import com.cybersoft.dto.UserDto;
import com.cybersoft.entity.Course;
import com.cybersoft.entity.UserCourse;

public interface CourseService {
	void save(CourseDto dto);
	List<CourseDto> getAll();
	CourseDto getById(int id);
	void update(CourseDto dto);
	void delete(int id);
	List<CourseDto> getCourseByUser(String email);
	List<CourseDto> getCourseByCategory(int id);
	CourseDto getTheLastCourse();

}
