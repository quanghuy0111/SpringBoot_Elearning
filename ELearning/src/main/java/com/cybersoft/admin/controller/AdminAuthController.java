package com.cybersoft.admin.controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cybersoft.dto.LoginDto;
import com.cybersoft.service.AuthService;

@RestController
@RequestMapping("api/admin/auth")
public class AdminAuthController {

	private AuthService authService;

	public AdminAuthController(AuthService authService) {
		this.authService = authService;
	}

	//Gọi API để đăng nhập vào hệ thống , trả về một chuỗi JWT nếu đăng nhập thành công
	@PostMapping("login")
	public Object post(@Valid @RequestBody LoginDto dto) {
		try {
			String token = authService.login(dto);
			return new ResponseEntity<Object>(token, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
		}
	}
}
