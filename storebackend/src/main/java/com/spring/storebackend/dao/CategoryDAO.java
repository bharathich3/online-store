package com.spring.storebackend.dao;

import java.util.List;

import com.spring.storebackend.dto.Category;

public interface CategoryDAO {
	
	public Category get(int id);

	public List<Category> list();

	boolean add(Category category);
	
	boolean update(Category category);

	boolean delete(Category category);


}
