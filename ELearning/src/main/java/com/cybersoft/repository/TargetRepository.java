package com.cybersoft.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cybersoft.dto.TargetDto;
import com.cybersoft.dto.VideoDto;
import com.cybersoft.entity.Target;

@Repository
public interface TargetRepository extends JpaRepository<Target, Integer> {
	//trả về list target của tất cả các khóa học
	@Query("SELECT new com.cybersoft.dto.TargetDto(t.id, t.title,c.description) FROM Target t JOIN Course c ON t.courseId = c.id")
	public List<TargetDto> findAllJoin();
	
	//trả về list target thuộc về một khóa học
	@Query("SELECT new com.cybersoft.dto.TargetDto(t.id, t.title,c.description) FROM Target t JOIN Course c ON t.courseId = c.id WHERE c.id = ?1")
	public List<TargetDto> findTargetsByCourse(int id);
}
