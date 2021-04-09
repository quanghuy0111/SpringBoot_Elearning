package com.cybersoft.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cybersoft.dto.TargetDto;
import com.cybersoft.entity.Target;
import com.cybersoft.repository.TargetRepository;
import com.cybersoft.service.TargetService;

@Service
public class TargetServiceImpl implements TargetService {
	private TargetRepository targetRepository;
	public TargetServiceImpl(TargetRepository targetRepository) {
		this.targetRepository = targetRepository;
	}
	@Override
	public void save(TargetDto dto) {
		Target entity = new Target(dto.getId(), dto.getTitle(), dto.getCourseId());
		targetRepository.save(entity);
		
		
	}

	@Override
	public List<TargetDto> getAll() {
		List<TargetDto> dtos = targetRepository.findAllJoin();
		return dtos;
	}

	@Override
	public TargetDto getById(int id) {
		Target entity = targetRepository.findById(id).get();
		TargetDto dto = new TargetDto(entity.getId(), entity.getTitle(), entity.getCourseId());
		return dto;
	}

	@Override
	public void edit(TargetDto dto) {
		Target entity = targetRepository.findById(dto.getId()).get();
		entity.setTitle(dto.getTitle());
		entity.setCourseId(dto.getCourseId());
		targetRepository.save(entity);
		
	}

	@Override
	public void delete(int id) {
		targetRepository.deleteById(id);
		
	}
	@Override
	public List<TargetDto> getByCourse(int id) {
		List<TargetDto> dtos = targetRepository.findTargetsByCourse(id);
		return dtos;
	}

}
