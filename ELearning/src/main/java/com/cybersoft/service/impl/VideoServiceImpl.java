package com.cybersoft.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cybersoft.dto.UserDto;
import com.cybersoft.dto.VideoDto;
import com.cybersoft.entity.User;
import com.cybersoft.entity.Video;
import com.cybersoft.repository.VideoRepository;
import com.cybersoft.service.VideoService;

@Service
public class VideoServiceImpl implements VideoService {
	private VideoRepository videoRepository;

	public VideoServiceImpl(VideoRepository videoRepository) {
		this.videoRepository = videoRepository;
	}

	@Override
	public void save(VideoDto dto) {
		Video entity = new Video(dto.getTitle(), dto.getUrl(), dto.getImage(), dto.getTimeCount(),dto.getCourseId());
		videoRepository.save(entity);

	}

	@Override
	public List<VideoDto> getAll() {
		List<VideoDto> dtos = videoRepository.findAllJoin();
		return dtos;
	}

	@Override
	public VideoDto getById(int id) {
		Video entity = videoRepository.findById(id).get();


		VideoDto dto = new VideoDto(
				entity.getId(),
				entity.getTitle(),
				entity.getUrl(),
				entity.getImage(),

				entity.getTimeCount(),
				entity.getCourseId()
				);
		return dto;
	}

	@Override
	public void edit(VideoDto dto) {
		Video entity = videoRepository.findById(dto.getId()).get();
		entity.setTitle(dto.getTitle());
		entity.setUrl(dto.getUrl());
		entity.setImage(dto.getImage());
		entity.setTimeCount(dto.getTimeCount());
		videoRepository.save(entity);

	}

	@Override
	public void delete(int id) {
		videoRepository.deleteById(id);

	}

	@Override
	public List<VideoDto>  getByCourse(int id) {
		List<VideoDto> dtos = videoRepository.findVideosByUser(id);
		return dtos;
	}




}
