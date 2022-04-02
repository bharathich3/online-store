package com.spring.storebackend.dao;

import java.util.List;

import com.spring.storebackend.dto.Category;

public interface CategoryDAO {

	public List<Category> list();

	public Category get(int id);
	
}
