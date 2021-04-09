package com.cybersoft.admin.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cybersoft.common.IndentifyEmail;
import com.cybersoft.common.IndentifyRole;
import com.cybersoft.dto.CourseDto;
import com.cybersoft.dto.UserDto;
import com.cybersoft.dto.VideoDto;
import com.cybersoft.service.CourseService;
import com.cybersoft.service.VideoService;

@RestController
@RequestMapping("api/admin/video")
public class AdminVideoController {
	private VideoService videoService;
	private CourseService courseService;

	public AdminVideoController(VideoService videoService,CourseService courseService) {
		this.videoService = videoService;
		this.courseService = courseService;
	}

	//Lấy ra danh sách video
	@GetMapping("")
	public Object get() {
		try {
			List<VideoDto> dtos ;
			//Nếu là Admin thì sẽ lấy ra toàn bộ
			if(IndentifyRole.getRolePrincipal().contains("ROLE_ADMIN")) {
				dtos = videoService.getAll();
			}

			else {
				dtos = new ArrayList<VideoDto>();
				//Nếu là Teacher thì sẽ lấy ra các video thuộc về khóa học của user
				List<CourseDto> courseDtos = courseService.getCourseByUser(IndentifyEmail.getEmailPrincipal());
				for(CourseDto dto : courseDtos) {
					List<VideoDto> videos = videoService.getByCourse(dto.getId());
					dtos.addAll(videos);
				}


			}
			return new ResponseEntity<Object>(dtos, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
		}

	}

	//Thêm mới một video
	@PostMapping("")
	public Object save(@RequestBody VideoDto dto) {

		try {
			videoService.save(dto);;
			return new ResponseEntity<Object>(HttpStatus.CREATED);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
	}

	//Edit một video
	@PutMapping("{id}")
	public Object put(@PathVariable int id,@RequestBody VideoDto dto) {
		try {
			videoService.edit(dto);

			return new ResponseEntity<Object>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
		}

	}

	//Xóa một video
	@DeleteMapping("{id}")
	public Object delete(@PathVariable int id) {
		try {
			System.out.println(id);
			videoService.delete(id);;
			return new ResponseEntity<Object>(HttpStatus.OK);


		} catch (Exception e) {
			return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
		}
	}
}
