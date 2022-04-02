package com.spring.storebackend.daoimpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.spring.storebackend.dao.CategoryDAO;
import com.spring.storebackend.dto.Category;
@Repository("categoryDAO")
public class CategoryDAOImpl implements CategoryDAO {

	private static List<Category> categories = new ArrayList<>();
	static {
		Category category = new Category();

		// adding first Category
		category.setId(1);
		category.setName("Television");
		category.setDescription("This is some description for Television");
		category.setImageURL("CAT_1.png");
		categories.add(category);

		// adding second Category

		category = new Category();

		category.setId(2);
		category.setName("Mobile");
		category.setDescription("This is some description for Mobile");
		category.setImageURL("CAT_2.png");
		categories.add(category);
		
		// adding thrid Category

		category = new Category();

		category.setId(3);
		category.setName("Tab");
		category.setDescription("This is some description for Tab");
		category.setImageURL("CAT_3.png");
		categories.add(category);
	}

	@Override
	public List<Category> list() {
		// TODO Auto-generated method stub
		return categories;
	}

	@Override
	public Category get(int id) {
		//enchanced for loop
		for(Category category:categories) {
			if(category.getId()==id) return category;
		}
		return null;
	}

}
