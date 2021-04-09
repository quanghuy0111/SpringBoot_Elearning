package com.cybersoft.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

public class LoginDto {
	
	@NotEmpty(message = "Vui lòng nhập email!")
	@Email(message = "Email không đúng định dạng!")
	private String email;
	
	@NotEmpty(message = "Vui lòng nhập mật khẩu!")
	@Length(min = 6, message = "Mật khẩu ít nhất 6 ký tự!")
	private String password;
	
	public LoginDto() {}

	public LoginDto(String email, String password) {
		super();
		this.email = email;
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
