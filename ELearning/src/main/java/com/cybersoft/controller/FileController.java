package com.cybersoft.controller;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
//Controller upload file của user Student
@RestController
@RequestMapping("api/user/file")
public class FileController {
	
	@Value("${file.upload-dir-category}")
	private String uploadDir;
	
	@Value("${file.upload-dir-video}")
	private String uploadDirVideo;
	
	@Value("${file.upload-dir-profile}")
	private String uploadDirProfile;
	
	//upload vào folder category
	@PostMapping("upload")
	public Object upload(@RequestParam MultipartFile file) {
		try {
			String fileName = file.getOriginalFilename();
			System.out.println(fileName);
			Path filePath = Paths.get(uploadDir + fileName);
			System.out.println(filePath);
			Files.write(filePath,file.getBytes());
			
			return new ResponseEntity<Object>(fileName,HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
	}
	
	//upload vào folder video
	@PostMapping("upload/video")
	public Object uploadVideo(@RequestParam MultipartFile file) {
		try {
			String fileName = file.getOriginalFilename();
			System.out.println(fileName);
			Path filePath = Paths.get(uploadDirVideo + fileName);
			System.out.println(filePath);
			Files.write(filePath,file.getBytes());
			
			return new ResponseEntity<Object>(fileName,HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
	}
	
	//upload vào folder profile
	@PostMapping("upload/profile")
	public Object uploadProfile(@RequestParam MultipartFile file) {
		try {
			String fileName = file.getOriginalFilename();
			System.out.println(fileName);
			Path filePath = Paths.get(uploadDirProfile + fileName);
			System.out.println(filePath);
			Files.write(filePath,file.getBytes());
			
			return new ResponseEntity<Object>(fileName,HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
	}
	
}
