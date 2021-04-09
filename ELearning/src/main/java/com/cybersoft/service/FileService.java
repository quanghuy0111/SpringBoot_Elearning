package com.cybersoft.service;

import org.springframework.web.multipart.MultipartFile;

public interface FileService {
	String saveFileCategory(MultipartFile file);
	String saveFileVideo(MultipartFile file);
	String saveFileProfile(MultipartFile file);
	String saveFileCourse(MultipartFile file);
}
