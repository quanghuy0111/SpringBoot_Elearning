package com.cybersoft.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//Class Cấu hình cho phép Cross Origin
@Configuration
public class CorsConfig implements WebMvcConfigurer {
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/api/**")
		.allowedOrigins("*")
		.allowedMethods("GET","POST","PUT","DELETE","OPTIONS")
		.allowCredentials(false)
		.maxAge(4800);
	}
}
