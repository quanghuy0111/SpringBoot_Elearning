package com.cybersoft.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cybersoft.dto.UserCourseDto;
import com.cybersoft.service.UserCourseService;

@RestController
@RequestMapping("api/user/userCourse")
public class User_UserCourseController {
	private UserCourseService userCourseService;
	public User_UserCourseController(UserCourseService userCourseService) {
		this.userCourseService = userCourseService;
	}
	
	//Thêm mới 1 User Course
	@PostMapping("")
	public Object post(@RequestBody UserCourseDto dto) {
		try {
			userCourseService.save(dto);
			return new ResponseEntity<Object>(HttpStatus.CREATED);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
		}
	}
	
	//Xóa User Course
	@DeleteMapping("")
	public Object delete(@RequestBody UserCourseDto dto) {
		try {
			userCourseService.delete(dto);
			return new ResponseEntity<Object>(HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
		}
	}
}
