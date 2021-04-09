package com.cybersoft.common;

import java.util.Collection;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

//Class trả về Role Name của user đang tương tác với hệ thống
public class IndentifyRole {
	public static String getRolePrincipal() {
		Collection<SimpleGrantedAuthority> authorities = (Collection<SimpleGrantedAuthority>)    SecurityContextHolder.getContext().getAuthentication().getAuthorities();
		return authorities.toString();
	}
}
