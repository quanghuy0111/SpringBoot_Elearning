package com.cybersoft.service;

import java.util.List;

import com.cybersoft.dto.VideoDto;

public interface VideoService {
	void save(VideoDto dto);
	List<VideoDto> getAll();
	VideoDto getById(int id);
	void edit(VideoDto dto);
	void delete(int id);
	List<VideoDto>  getByCourse(int id);
}
