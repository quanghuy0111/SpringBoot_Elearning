package com.cybersoft.admin.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cybersoft.dto.CategoryDto;
import com.cybersoft.dto.UserDto;
import com.cybersoft.service.CategoryService;

@RestController
@RequestMapping("api/admin/category")
public class AdminCategoryController {
	private CategoryService categoryService;
	public AdminCategoryController(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

//Lấy toàn bộ category
	@GetMapping("")
	public Object get() {
		try {
			List<CategoryDto> dtos = categoryService.getAll();

			return new ResponseEntity<Object>(dtos, HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
		}
	}

	//Thêm mới một category
	@PostMapping("")
	public Object save(@Valid @RequestBody CategoryDto dto) {

		try {
			categoryService.save(dto);
			return new ResponseEntity<Object>(HttpStatus.CREATED);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
	}
	
	
	//Edit một category
	@PutMapping("{id}")
	public Object put(@PathVariable int id,@RequestBody CategoryDto dto) {
		try {
			if(categoryService.getById(id)==null) {
				return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
			}
			else {

				categoryService.edit(dto);
				return new ResponseEntity<Object>(HttpStatus.OK);
			}

		} catch (Exception e) {
			return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
		}
	}

	//Xóa Category
	@DeleteMapping("{id}")
	public Object delete(@PathVariable int id) {
		try {
			System.out.println(id);
			categoryService.delete(id);;
			return new ResponseEntity<Object>(HttpStatus.OK);


		} catch (Exception e) {
			return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
		}
	}

}
