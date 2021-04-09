package com.cybersoft.service;

import java.util.List;

import com.cybersoft.entity.Role;
import com.cybersoft.dto.RoleDto;

public interface RoleService {
	void save(RoleDto dto);
	List<RoleDto> getAll();
	RoleDto getById(int id);
	void edit(RoleDto dto);
	void delete(int id);
}
