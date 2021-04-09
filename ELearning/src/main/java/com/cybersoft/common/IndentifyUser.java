package com.cybersoft.common;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import com.cybersoft.dto.UserDetailDto;
//Class trả về id của user đang tương tác với hệ thống
public class IndentifyUser {
	public static int getIdPrincipal() {
		
		UserDetailDto userDetail = (UserDetailDto) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return userDetail.getId();
	}
}
