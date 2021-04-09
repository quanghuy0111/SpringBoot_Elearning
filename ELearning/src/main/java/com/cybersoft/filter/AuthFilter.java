package com.cybersoft.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.cybersoft.entity.User;
import com.cybersoft.repository.RoleRepository;
import com.cybersoft.repository.UserRepository;

import io.jsonwebtoken.Jwts;

public class AuthFilter extends BasicAuthenticationFilter {
	private UserDetailsService userDetailsService;
	
	public AuthFilter(AuthenticationManager authenticationManager, UserDetailsService userDetailsService) {
			super(authenticationManager);
			this.userDetailsService = userDetailsService;
			
		
	}
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		//Các Api bắt đầu bằng các ký tự này sẽ được đi qua filter mà không cần Token
		if(request.getServletPath().startsWith("/api/admin/auth") || request.getServletPath().startsWith("/api/auth") || request.getServletPath().startsWith("/api/register")) {
			chain.doFilter(request, response);
			return;
		}
		//Lấy token để giải mã ra thông tin email , nếu hợp lệ thì cho đi qua filter 
		String tokenHeader = request.getHeader("Authorization");
		if(tokenHeader != null && !tokenHeader.isEmpty() && tokenHeader.startsWith("Bearer ")) {
			String token = tokenHeader.replace("Bearer ","");
			
			String email = Jwts.parser()
			.setSigningKey("ABC_EGH")
			.parseClaimsJws(token)
			.getBody()
			.getSubject();	
			
			UserDetails userDetails = userDetailsService.loadUserByUsername(email);
			
			
			
			
			Authentication authentication = 
					new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
			
			SecurityContextHolder.getContext().setAuthentication(authentication);
			chain.doFilter(request, response);
			
			}
		else {
			//Nếu không hợp lệ thì trả về response này
			response.sendError(401,"Chưa đăng nhập");
		}
	}
	
	
}
