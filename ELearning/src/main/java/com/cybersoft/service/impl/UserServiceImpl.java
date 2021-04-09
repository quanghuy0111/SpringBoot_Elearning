package com.cybersoft.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

import com.cybersoft.dto.UserDto;
import com.cybersoft.entity.User;
import com.cybersoft.entity.UserCourse;
import com.cybersoft.repository.UserRepository;
import com.cybersoft.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	
	private UserRepository userRepository;

	
	public UserServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;

	}

	//Lấy toàn bộ thông tin User để hiển thị lên Front End
	@Override
	public List<UserDto> getAll() {
		
		List<UserDto> dtos = userRepository.findAllJoin();
		return dtos;
	}

	//Edit User
	@Override
	public void update(UserDto dto) {
		// TRUY VẤN LẤY RA DỮ LIỆU ĐANG LƯU TRONG DB
		User entity = userRepository.findById(dto.getId()).get();
		
		//Kiểm tra data trên Front End trả về , nếu khác null hoặc rỗng thì set	
		if(dto.getFullname() != null && !dto.getFullname().equalsIgnoreCase("")) {
			entity.setFullname(dto.getFullname());
		}
		if(dto.getEmail() != null && !dto.getEmail().isEmpty()) {
			entity.setEmail(dto.getEmail());
		}
		if(dto.getAvatar()!=null && !dto.getAvatar().isEmpty()) {
			entity.setAvatar(dto.getAvatar());
		}
		
		if(dto.getPassword() != null && !dto.getPassword().isEmpty()) {
			String hashed = BCrypt.hashpw(dto.getPassword(), BCrypt.gensalt());
			entity.setPassword(hashed);
		}
		
		
		
		
		
		entity.setAddress(dto.getAddress());
		entity.setPhone(dto.getPhone());
		entity.setRoleId(dto.getRoleId());
		
		
		
		userRepository.save(entity);

		
	}
	
	
	//Xóa user
	@Override
	public void delete(int id) {
		userRepository.deleteById(id);
	}

	
	//Get User theo id
	@Override
	public UserDto getById(int id) {
		User entity = userRepository.findById(id).get();
		
		System.out.println(entity.getFullname());
		UserDto dto = new UserDto(
				entity.getId(),
				entity.getFullname(),
				entity.getEmail(),
				entity.getPassword(),
				
				entity.getAvatar(),
				entity.getRoleId()
				
		
			);
	
		return dto;
	}

	//Thêm mới một user
	@Override
	public void insert(UserDto dto) {
		String hashed = BCrypt.hashpw(dto.getPassword(), BCrypt.gensalt());
		// MAPPING USER DTO SANG USER ENTITY
		User entity = new User();
		entity.setEmail(dto.getEmail());
		entity.setPassword(hashed);
		entity.setFullname(dto.getFullname());
		entity.setAvatar(dto.getAvatar());
		entity.setRoleId(dto.getRoleId());
		entity.setUserCourses(dto.getUserCourses());
		userRepository.save(entity);
		
	}

	//Lấy các user là Student thuộc về khóa học
	@Override
	public List<UserDto> getStudentOfCourse(int id) {
		List<UserDto> dtos = userRepository.findAllUserOfCourse(id);
		
		return dtos;
	}

	//Lấy user cuối cùng trong danh sách
	@Override
	public UserDto getTheLastUser() {
		User entity= userRepository.findTop1ByOrderByIdDesc();
		UserDto dto = new UserDto();
		dto.setId(entity.getId());
		dto.setFullname(entity.getFullname());
		dto.setEmail(entity.getEmail());
		dto.setRoleId(entity.getRoleId());
		return dto;
		
	}

	//Tìm kiếm user theo email
	@Override
	public UserDto getByEmail(String email) {
		User entity = userRepository.findByEmail(email);
		UserDto dto = new UserDto();
		dto.setId(entity.getId());
		return dto;
	}

	@Override
	public void insertUserRegister(UserDto dto) {
		String hashed = BCrypt.hashpw(dto.getPassword(), BCrypt.gensalt());
		// MAPPING USER DTO SANG USER ENTITY
		User entity = new User();
		entity.setEmail(dto.getEmail());
		entity.setPassword(hashed);
		entity.setFullname(dto.getFullname());
		entity.setRoleId(3);
		
		
		userRepository.save(entity);
		
	}

	@Override
	public void updateProfile(UserDto dto) {
		User entity = userRepository.findById(dto.getId()).get();
		if(dto.getEmail() != null && !dto.getEmail().isEmpty()) {
			entity.setEmail(dto.getEmail());
		}
		if(dto.getFullname() != null && !dto.getFullname().isEmpty()) {
			entity.setFullname(dto.getFullname());
		}
		if(dto.getAddress() != null && !dto.getAddress().isEmpty()) {
			entity.setAddress(dto.getAddress());
		}
		if(dto.getPhone() != null && !dto.getPhone().isEmpty()) {
			entity.setPhone(dto.getPhone());
		}
		if(dto.getAvatar() != null && !dto.getAvatar().isEmpty()) {
			entity.setAvatar(dto.getAvatar());
		}
		if(dto.getPassword() != null && !dto.getPassword().isEmpty()) {
			String hashed = BCrypt.hashpw(dto.getPassword(), BCrypt.gensalt());
			entity.setPassword(hashed);
		}
		
		userRepository.save(entity);
		
	}

}
