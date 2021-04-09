package com.cybersoft.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.cybersoft.common.PasswordEnconderTest;
import com.cybersoft.filter.AuthFilter;


@Configuration
@EnableWebSecurity
@Order(1)
public class AdminSecurityConfig extends WebSecurityConfigurerAdapter {
	private UserDetailsService userDetailsService;
	public AdminSecurityConfig(UserDetailsService userDetailsService) {
		this.userDetailsService = userDetailsService;
	}
	
	//Phương thức mã hóa
	 @Bean 
	 public PasswordEncoder passwordEncoder() { 
		 return new BCryptPasswordEncoder();
		 }
	 //Gọi hàm kiểm tra đăng nhập
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		// TODO Auto-generated method stub
		return super.authenticationManagerBean();
	}

	//Khai báo service lấy thông tin user từ db và khai báo phương thức mã hóa password
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// TODO Auto-generated method stub
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}

	//Phân quyền
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors();
		http.csrf().disable() // TẮT CHỨC NĂNG CHỐNG TẤN CÔNG GIẢ MẠO REQUEST
		.antMatcher("/api/admin/**") 
		.authorizeRequests()
		.antMatchers("/api/admin/auth/login")
		.permitAll()
		.antMatchers("/api/admin/role/**")
		.hasAnyRole("ADMIN")
		.antMatchers("/api/admin/user/**")
		.hasAnyRole("ADMIN", "TEACHER")
		.anyRequest()
		.authenticated();
		
		//Add thêm Filter
		http.addFilter(new AuthFilter(authenticationManager(), userDetailsService));
		//Không cho phép sử dụng session
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	}
	
	//bỏ xác thực trang Swagger
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring()
		.antMatchers("/v2/api-docs",
		"/configuration/ui",
		"/swagger-resources/**",
		"/configuration/security",
		"/swagger-ui.html",
		"/webjars/**");
	}
	
	
	
}
