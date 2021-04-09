package com.cybersoft.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cybersoft.common.IndentifyEmail;
import com.cybersoft.common.IndentifyRole;
import com.cybersoft.common.IndentifyUser;
import com.cybersoft.dto.CategoryDto;
import com.cybersoft.dto.CourseDto;
import com.cybersoft.service.CourseService;
import com.cybersoft.service.UserService;

import javassist.expr.NewArray;

import java.util.List;


@RestController
@RequestMapping("api/user/course")
public class UserCourseController {
	private CourseService courseService;
	public UserCourseController (CourseService courseService) {
		this.courseService = courseService;
	}
	
	//Lấy Toàn bộ Course
	@GetMapping("public")
	public Object get() {
		try {
			
			List<CourseDto> dtos = courseService.getAll();
			
			return new ResponseEntity<Object>(dtos, HttpStatus.ACCEPTED);
		} catch (Exception e) {
			return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
		}
	}
	
	//Lấy toàn bộ Course thuộc category
	@GetMapping("public/category/{id}")
	public Object getCourseOfCategory(@PathVariable int id) {
		try {
			List<CourseDto> dtos = courseService.getCourseByCategory(id);
			
			return new ResponseEntity<Object>(dtos, HttpStatus.OK);
			
		} catch (Exception e) {
			System.out.println("Lỗi");
			return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
		}

	}
	
	//Lấy toàn bộ course thuộc về user Student mà user đã ghi danh
	@GetMapping("details")
	public Object getCourseOfUser() {
		try {
			
			List<CourseDto> dtos  = courseService.getCourseByUser(IndentifyEmail.getEmailPrincipal());
			

			return new ResponseEntity<Object>(dtos, HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("public/{id}")
	public Object getById(@PathVariable int id) {
		try {
			CourseDto dto = courseService.getById(id);
			return new ResponseEntity<Object>(dto, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
		}
		
	}
	
	@PostMapping("")
	public Object post(@RequestBody CourseDto dto) {
		try {
			courseService.save(dto);
			return new ResponseEntity<Object>(HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
		}
	}
	
	
	
	

}
