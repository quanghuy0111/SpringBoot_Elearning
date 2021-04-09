package com.cybersoft.common;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;


//Class trả về Email của User đang tương tác với hệ thống
public class IndentifyEmail {
	public static String getEmailPrincipal() {
		String email;
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(principal instanceof UserDetails) {
			email = ((UserDetails)principal).getUsername();
			
		}else {
			email = principal.toString();
		}
		return email;
	}
}
