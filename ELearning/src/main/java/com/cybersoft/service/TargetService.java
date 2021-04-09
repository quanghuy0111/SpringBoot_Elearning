package com.cybersoft.service;

import java.util.List;

import com.cybersoft.dto.TargetDto;

public interface TargetService {
	void save(TargetDto dto);
	List<TargetDto> getAll();
	TargetDto getById(int id);
	void edit(TargetDto dto);
	void delete(int id);
	List<TargetDto> getByCourse(int id);
}	
