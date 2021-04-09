package com.cybersoft.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cybersoft.dto.CategoryDto;
import com.cybersoft.service.CategoryService;
import com.cybersoft.service.CourseService;

@RestController
@RequestMapping("api/user/category")
public class UserCategoryController {
	private CategoryService categoryService;
	
	public UserCategoryController(CategoryService categoryService) {
		this.categoryService = categoryService;
		
	}
	
	//Get toàn bộ category
	@GetMapping("public")
	public Object get() {
		try {
			List<CategoryDto> dtos = categoryService.getAll();

			return new ResponseEntity<Object>(dtos, HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
		}
	}


}