package com.cybersoft.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cybersoft.entity.Course;
import com.cybersoft.entity.User;
import com.cybersoft.dto.CourseDto;

@Repository
public interface CourseRepository extends JpaRepository<Course,Integer> {
	//Trả về một đối tượng CourseDto thuộc user đang tương tác với hệ thống
	@Query("SELECT new com.cybersoft.dto.CourseDto(c.id, c.title, c.image, c.lecturesCount,c.price,c.hourCount,c.description,c.discount,c.promotionPrice,c.viewCount) FROM UserCourse uc JOIN Course c ON uc.course.id = c.id JOIN User u ON uc.user.id = u.id WHERE u.email = ?1")
	public List<CourseDto> getCourseByUser(String email);
	
	
	//Trả về một đối tượng CourseDto thuộc Category
	@Query("SELECT new com.cybersoft.dto.CourseDto(c.id, c.title, c.image, c.lecturesCount,c.price,c.hourCount,c.description,c.discount,c.promotionPrice,c.viewCount,c.content) FROM Course c JOIN Category ca ON c.category.id = ca.id WHERE ca.id = ?1 ")
	public List<CourseDto> getCourseByCategory(int id);
	
	//Tìm Course cuối cùng trong danh sách
	public Course findTop1ByOrderByIdDesc();
}
