package com.cybersoft.service.impl;

//Tầng Service thực hiện chức năng upload hình ảnh
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cybersoft.service.FileService;
@Service
public class FileServiceImpl implements FileService {
	
	//Biến nhận giá trị đường dẫn đến folder category
	@Value("${file.upload-dir-category}")
	private String uploadDir;
	
	//Biến nhận giá trị đường dẫn đến folder video
	@Value("${file.upload-dir-video}")
	private String uploadDirVideo;
	
	//Biến nhận giá trị đường dẫn đến folder profile
	@Value("${file.upload-dir-profile}")
	private String uploadDirProfile;
	
	//Biến nhận giá trị đường dẫn đến folder course
	@Value("${file.upload-dir-course}")
	private String uploadDirCourse;

	//Lưu trữ file vào folder category
	@Override
	public String saveFileCategory(MultipartFile file) {
		try {
			String fileName = file.getOriginalFilename();
			Path filePath = Paths.get(uploadDir + fileName);
			
			Files.write(filePath, file.getBytes());
			
			return fileName;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}
	
	//Lưu trữ file vào folder video
	@Override
	public String saveFileVideo(MultipartFile file) {
		try {
			String fileName = file.getOriginalFilename();
			Path filePath = Paths.get(uploadDirVideo + fileName);
			
			Files.write(filePath, file.getBytes());
			
			return fileName;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}
	
	//Lưu trữ file vào folder profile
	@Override
	public String saveFileProfile(MultipartFile file) {
		try {
			String fileName = file.getOriginalFilename();
			Path filePath = Paths.get(uploadDirProfile + fileName);
			
			Files.write(filePath, file.getBytes());
			
			return fileName;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	//Lưu trữ file vào folder course
	@Override
	public String saveFileCourse(MultipartFile file) {
		try {
			String fileName = file.getOriginalFilename();
			Path filePath = Paths.get(uploadDirCourse + fileName);
			
			Files.write(filePath, file.getBytes());
			
			return fileName;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}
	
	
}
