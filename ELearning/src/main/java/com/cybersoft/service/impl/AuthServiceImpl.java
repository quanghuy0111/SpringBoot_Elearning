package com.cybersoft.service.impl;

import java.util.Date;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import com.cybersoft.dto.LoginDto;
import com.cybersoft.service.AuthService;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class AuthServiceImpl implements AuthService {
	private AuthenticationManager authenticationManager;
	 public AuthServiceImpl(AuthenticationManager authenticationManager) {
		 
		this.authenticationManager = authenticationManager;
	}
	 //Phương thức login tầng Service trả về token khi user được xác thực
	@Override
	public String login(LoginDto dto) {
		authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(dto.getEmail(), dto.getPassword()));
		
		Date now = new Date();
		String token = Jwts.builder()
				.setSubject(dto.getEmail()) // GẮN email VÀO TOKEN
				.setIssuedAt(now) // THỜI GIAN PHÁT HÀNH TOKEN
				.setExpiration(new Date(now.getTime() + 864000000L)) // HẠN SỬ DỤNG 10 NGÀY
				.signWith(SignatureAlgorithm.HS512, "ABC_EGH") // THUẬT TOÁN MÃ HÓA
				.compact();
		return token;
	}
	
}
