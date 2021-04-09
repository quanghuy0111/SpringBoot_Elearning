package com.cybersoft.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cybersoft.dto.VideoDto;
import com.cybersoft.service.VideoService;

@RestController
@RequestMapping("api/user/video/public")
public class UserVideoController {
	private VideoService videoService;
	
	public UserVideoController(VideoService videoService) {
		this.videoService = videoService;
	}
	
	//Get video thuộc về khóa học
	@GetMapping("{id}")
	public Object get(@PathVariable int id) {
		try {
			List<VideoDto> dtos = videoService.getByCourse(id);
			return new ResponseEntity<Object>(dtos, HttpStatus.ACCEPTED);
		} catch (Exception e) {
			return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
		}
	}
}
