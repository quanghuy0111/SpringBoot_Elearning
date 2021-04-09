package com.cybersoft.admin.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.cybersoft.service.FileService;

@RestController
@RequestMapping("api/admin/file")
public class AdminFileController {
	
	private FileService fileService;
	
	public AdminFileController(FileService fileService) {
		this.fileService = fileService;
	}
	
	//Upload file vào thư mục category
	@PostMapping("category")
	public Object upload(@RequestParam MultipartFile file) {
		try {
			String fileName = fileService.saveFileCategory(file);
			return new ResponseEntity<Object>(fileName, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
	}
	
	//Upload file vào thư mục video
	@PostMapping("video")
	public Object uploadVideo(@RequestParam MultipartFile file) {
		try {
			String fileName = fileService.saveFileVideo(file);
			return new ResponseEntity<Object>(fileName, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
	}
	
	//Upload file vào thư mục profile
	@PostMapping("profile")
	public Object uploadProfile(@RequestParam MultipartFile file) {
		try {
			String fileName = fileService.saveFileProfile(file);
			return new ResponseEntity<Object>(fileName, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
	}
	
	//Upload file vào thư mục course
	@PostMapping("course")
	public Object uploadCourse(@RequestParam MultipartFile file) {
		try {
			String fileName = fileService.saveFileCourse(file);
			return new ResponseEntity<Object>(fileName, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
	}
	
	
}
