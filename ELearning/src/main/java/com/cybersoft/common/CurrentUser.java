package com.cybersoft.common;

import org.springframework.security.core.context.SecurityContextHolder;

import com.cybersoft.dto.UserDetailDto;
import com.cybersoft.dto.UserDto;

//Class trả về ID và Role ID của user đang tương tác với hệ thống
public class CurrentUser {
public static UserDto getCurrentUser() {
		
		UserDetailDto userDetail = (UserDetailDto) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserDto dto = new UserDto();
		dto.setId(userDetail.getId());
		dto.setRoleId(userDetail.getRoleId());
		
		return dto;
	}
}
