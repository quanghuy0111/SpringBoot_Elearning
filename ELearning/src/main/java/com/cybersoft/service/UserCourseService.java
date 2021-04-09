package com.cybersoft.service;

import com.cybersoft.dto.UserCourseDto;

public interface UserCourseService {
	void save(UserCourseDto dto);
	void delete(UserCourseDto dto);
}
