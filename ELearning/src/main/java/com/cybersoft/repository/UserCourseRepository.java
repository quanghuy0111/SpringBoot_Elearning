package com.cybersoft.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cybersoft.entity.UserCourse;

@Repository
public interface UserCourseRepository extends JpaRepository<UserCourse, Integer> {
	
	 
	 //xóa UserCourse
	@Transactional
	@Modifying
	@Query("DELETE FROM UserCourse uc WHERE uc.user.id = ?1 AND uc.course.id = ?2")
	public void deleteByUserAndCourse(int userId,int courseId);
}
