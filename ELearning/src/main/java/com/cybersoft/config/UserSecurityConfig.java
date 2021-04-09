package com.cybersoft.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.cybersoft.filter.AuthFilter;

@Configuration
@EnableWebSecurity
@Order(2)
public class UserSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	//Phân quyền User Student
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors();
		
		http.csrf().disable() // TẮT CHỨC NĂNG CHỐNG TẤN CÔNG GIẢ MẠO REQUEST
		.antMatcher("/api/**") 
		.authorizeRequests()
		.antMatchers("/api/user")
		.authenticated();
		
		//Thêm filter
		http.addFilter(new AuthFilter(authenticationManager(), userDetailsService));
		
		// KHÔNG SỬ DỤNG SESSION
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	}
	
	//Các trang này không cần xác thực
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring()
		.antMatchers("/api/user/course/public/**","/api/user/auth/public/**","/api/user/category/public/**"
				,"/api/user/target/public/**",
				"/api/user/video/public/**","/api/user/register/public");
	}
}


