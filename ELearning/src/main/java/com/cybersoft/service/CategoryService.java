package com.cybersoft.service;

import java.util.List;

import com.cybersoft.dto.CategoryDto;
import com.cybersoft.dto.RoleDto;

public interface CategoryService {
	List<CategoryDto> getAll();
	CategoryDto getById(int id);
	void save(CategoryDto dto);
	void edit(CategoryDto dto);
	void delete(int id);
	
}
