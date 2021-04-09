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
import com.cybersoft.dto.TargetDto;
import com.cybersoft.dto.VideoDto;
import com.cybersoft.service.CourseService;
import com.cybersoft.service.TargetService;

@RestController
@RequestMapping("api/admin/target")
public class AdminTargetController {
	private TargetService targetService;
	private CourseService courseService;
	public AdminTargetController(TargetService targetService,CourseService courseService) {
		this.targetService = targetService;
		this.courseService = courseService;
	}
	
	//Lấy ra targer
	@GetMapping("")
	public Object get() {
		try {
			List<TargetDto> dtos ;
			//Nếu là user Admin sẽ lấy ra toàn bộ target
			if(IndentifyRole.getRolePrincipal().contains("ROLE_ADMIN")) {
				dtos = targetService.getAll();
			}

			else {
				dtos = new ArrayList<TargetDto>();
				//Nếu là user Teacher thì sẽ lấy tất cả các target thuộc về khóa học của user
				List<CourseDto> courseDtos = courseService.getCourseByUser(IndentifyEmail.getEmailPrincipal());
				for(CourseDto dto : courseDtos) {
					List<TargetDto> targets = targetService.getByCourse(dto.getId());
					dtos.addAll(targets);
				}


			}
			return new ResponseEntity<Object>(dtos, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
		}
	}
	
	//Thêm mới target
	@PostMapping("")
	public Object save(@RequestBody TargetDto dto) {

		try {
			targetService.save(dto);;
			return new ResponseEntity<Object>(HttpStatus.CREATED);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
	}
	
	//Edit Target
	@PutMapping("{id}")
	public Object put(@PathVariable int id,@RequestBody TargetDto dto) {
		try {
			targetService.edit(dto);
			
			return new ResponseEntity<Object>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
		}
		
	}

	//Xóa target
	@DeleteMapping("{id}")
	public Object delete(@PathVariable int id) {
		try {
			System.out.println(id);
			targetService.delete(id);;
			return new ResponseEntity<Object>(HttpStatus.OK);


		} catch (Exception e) {
			return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
		}
	}
}
