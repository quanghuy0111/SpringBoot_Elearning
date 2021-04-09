package com.cybersoft.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cybersoft.dto.TargetDto;
import com.cybersoft.service.TargetService;
import com.cybersoft.service.VideoService;

@RestController
@RequestMapping("api/user/target/public")
public class UserTargetController {
	private TargetService targetService;
	
	public UserTargetController(TargetService targetService) {
		this.targetService = targetService;
	}
	
	//get Target thuộc về khóa học
	@GetMapping("{id}")
	public Object get(@PathVariable int id) {
		try {
			List<TargetDto> dtos = targetService.getByCourse(id);
			return new ResponseEntity<Object>(dtos, HttpStatus.ACCEPTED);
		} catch (Exception e) {
			return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
		}
	}
}
