package com.cybersoft.service.impl;

import org.springframework.stereotype.Service;

import com.cybersoft.dto.CourseDto;
import com.cybersoft.dto.UserCourseDto;
import com.cybersoft.entity.UserCourse;
import com.cybersoft.repository.UserCourseRepository;
import com.cybersoft.service.UserCourseService;

@Service
public class UserCourseServiceImpl implements UserCourseService{
	private UserCourseRepository userCourseRepository;
	public UserCourseServiceImpl(UserCourseRepository userCourseRepository) {
		this.userCourseRepository = userCourseRepository;
	}
	@Override
	public void save(UserCourseDto dto) {
		UserCourse userCourse = new UserCourse(dto.getId(), dto.getUser(), dto.getCourse(), dto.getRoleId());
		userCourseRepository.save(userCourse);
		
		
	}
	@Override
	public void delete(UserCourseDto dto) {
		userCourseRepository.deleteByUserAndCourse(dto.getUser().getId(), dto.getCourse().getId());
		
		
	}
	
	
}
