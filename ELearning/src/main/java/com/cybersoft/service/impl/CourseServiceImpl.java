package com.cybersoft.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.cybersoft.common.IndentifyRole;
import com.cybersoft.dto.CategoryDto;
import com.cybersoft.dto.CourseDto;
import com.cybersoft.entity.Category;
import com.cybersoft.entity.Course;
import com.cybersoft.entity.User;
import com.cybersoft.entity.UserCourse;
import com.cybersoft.repository.CourseRepository;
import com.cybersoft.service.CourseService;

@Service
public class CourseServiceImpl implements CourseService {

	private CourseRepository courseRepository;

	public CourseServiceImpl(CourseRepository courseRepository) {
		this.courseRepository = courseRepository;
	}
	//Lấy toàn bộ course để hiển thị
	@Override
	public List<CourseDto> getAll() {
		List<CourseDto> dtos = new ArrayList<CourseDto>();
		try {
			List<Course> entities = courseRepository.findAll();
			for(Course entity : entities) {
				CourseDto dto = new CourseDto(entity.getId(), 
						entity.getTitle(), 
						entity.getImage(), 
						entity.getLeturesCount(),
						entity.getPrice());
				dto.setHourCount(entity.getHourCount());
				dto.setContent(entity.getContent());
				dtos.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dtos;
	}

	//Lấy course theo id
	@Override
	public CourseDto getById(int id) {
		Course entity = courseRepository.findById(id).get();

		CourseDto dto = new CourseDto();
		dto.setId(entity.getId());
		dto.setTitle(entity.getTitle());
		dto.setImage(entity.getImage());
		dto.setLectureCount(entity.getLeturesCount());
		dto.setHourCount(entity.getHourCount());
		dto.setViewCount(entity.getViewCount());
		dto.setDiscount(entity.getDiscount());
		dto.setDescription(entity.getDescription());
		dto.setPrice(entity.getPrice());
		dto.setPromotionPrice(entity.getPromotionPrice());
		dto.setContent(entity.getContent());

		return dto;

	}
	
	//Thêm mới một course
	@Override
	public void save(CourseDto dto) {
		Course entity = new Course();
		if(IndentifyRole.getRolePrincipal().contains("ROLE_ADMIN") || IndentifyRole.getRolePrincipal().contains("ROLE_TEACHER")) {
			entity.setTitle(dto.getTitle());
			entity.setImage(dto.getImage());
			entity.setLeturesCount(dto.getLectureCount());
			entity.setHourCount(dto.getHourCount());
			entity.setDiscount(dto.getDiscount());
			entity.setPrice(dto.getPrice());
			entity.setDescription(dto.getDescription());
			entity.setCategory(dto.getCategory());
			entity.setContent(dto.getContent());
		}


		courseRepository.save(entity);

	}





	//Edit một course
	@Override
	public void update(CourseDto dto) {
		Course entity = courseRepository.findById(dto.getId()).get();

		entity.setTitle(dto.getTitle());
		entity.setImage(dto.getImage());
		entity.setLeturesCount(dto.getLectureCount());
		entity.setHourCount(dto.getHourCount());
		entity.setDiscount(dto.getDiscount());
		entity.setPrice(dto.getPrice());
		entity.setDescription(dto.getDescription());
		
		
		
		entity.setCategory(dto.getCategory());
		entity.setContent(dto.getContent());


		courseRepository.save(entity);

	}
	
	//Xóa một course
	@Override
	public void delete(int id) {
		courseRepository.deleteById(id);

	}
	
	//Lấy toàn bộ khóa học thuộc về user theo email của user
	@Override
	public List<CourseDto> getCourseByUser(String email) {
		List<CourseDto> dtos = courseRepository.getCourseByUser(email);
		return dtos;

	}
	
	//Lấy toàn bộ course thuộc category
	@Override
	public List<CourseDto> getCourseByCategory(int id) {
		List<CourseDto> dtos = courseRepository.getCourseByCategory(id);
		return dtos;
	}
	
	//Lấy ra course đang nằm cuối danh sách
	@Override
	public CourseDto getTheLastCourse() {
		Course entity = courseRepository.findTop1ByOrderByIdDesc();
		CourseDto dto = new CourseDto();
		dto.setId(entity.getId());
		dto.setTitle(entity.getTitle());
		dto.setImage(entity.getImage());
		dto.setLectureCount(entity.getLeturesCount());
		dto.setHourCount(entity.getHourCount());
		dto.setViewCount(entity.getViewCount());
		dto.setDiscount(entity.getDiscount());
		dto.setDescription(entity.getDescription());
		dto.setPrice(entity.getPrice());
		dto.setPromotionPrice(entity.getPromotionPrice());

		return dto;
	}
	/*
	 * @Override public void updateUserCourse(CourseDto dto) { Course entity =
	 * courseRepository.findById(dto.getId()).get();
	 * 
	 * CourseDto dto_test = new CourseDto();
	 * 
	 * User user = new User(); user.setId(3); Course course = new Course();
	 * course.setId(111); int roleId = 1;
	 * 
	 * 
	 * UserCourse userCourse = new UserCourse();
	 * 
	 * userCourse.setUser(user); userCourse.setRoleId(roleId);
	 * userCourse.setCourse(course);
	 * 
	 * List<UserCourse> userCourses = new ArrayList<UserCourse>();
	 * 
	 * userCourses.add(userCourse);
	 * 
	 * entity.setUserCourses(userCourses); courseRepository.save(entity);
	 * 
	 * 
	 * }
	 */



}



