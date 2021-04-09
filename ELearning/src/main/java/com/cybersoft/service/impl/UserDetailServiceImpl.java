package com.cybersoft.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cybersoft.dto.UserDetailDto;
import com.cybersoft.entity.Role;
import com.cybersoft.entity.User;
import com.cybersoft.repository.RoleRepository;
import com.cybersoft.repository.UserRepository;
@Service
public class UserDetailServiceImpl implements UserDetailsService {
	private UserRepository userRepository;
	private RoleRepository roleRepository;
	public UserDetailServiceImpl(UserRepository userRepository,RoleRepository roleRepository) {
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user = userRepository.findByEmail(email);
		if(user == null) throw new UsernameNotFoundException("Không tìm thấy Email");
		
		Role role = roleRepository.findById(user.getRoleId()).get();
		
		//Role role = user.getRole();
		
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority(role.getDescription()));
		return new UserDetailDto(email, user.getPassword(), authorities,user.getId(),user.getRoleId());
	}
	
}
