package com.spring.storebackend.test;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.spring.storebackend.dao.CategoryDAO;
import com.spring.storebackend.dto.Category;

public class CategoryTestCase {
	
	private static AnnotationConfigApplicationContext context;
	
	private static CategoryDAO categoryDAO;
	
	private Category category;
	
	@BeforeClass
	public static void init() {
		
		context = new AnnotationConfigApplicationContext();
		context.scan("com.spring.storebackend");
		context.refresh();
		
		categoryDAO =(CategoryDAO)context.getBean("categoryDAO"); 
	}

/*	@Test
	public void testAddCategory() {
		category= new Category(); 
		
		category.setName("Television");
		category.setDescription("This is some description for Television");
		category.setImageURL("CAT_1.png");
		
		
		category.setName("Laptop");
		category.setDescription("This is some description for Laptop");
		category.setImageURL("CAT_2.png");
		
		category.setName("Mobile");
		category.setDescription("This is some description for Mobile");
		category.setImageURL("CAT_3.png");
		
		assertEquals("Successfully added a category inside the table", true,categoryDAO.add(category));
	}
*/

/*
	@Test
	public void testGetCategory() {
		category= categoryDAO.get(2);
		assertEquals("Successfully fetched a single category from the table", "Laptop",category.getName());;
	}
*/

/*	@Test
	public void testUpdateCategory() {
		category = categoryDAO.get(2);
		category.setName("Television");
       //category.setActive(true);
		assertEquals("Successfully updated a single Category in the table",true, categoryDAO.update(category));
		
	}
	*/

/*
	@Test
	public void testDeleteCategory() {
		category = categoryDAO.get(4);
		assertEquals("Successfully deleted a single Category in the table",true, categoryDAO.delete(category));
		
	}
*/
/*
	@Test
	public void testListCategory() {
		assertEquals("Successfully fetched the list of Category from the table",3, categoryDAO.list().size());
		
	}
*/
	
	//Integrating all Test cases in a SINGLE TEST CASE
	@Test
	public void testCRUDCategory() {
		
		//add operation
		category =new Category();

		category.setName("Laptop");
		category.setDescription("This is some description for Laptop");
		category.setImageURL("CAT_1.png");
		assertEquals("Something went wrong while inserting a new Category", true,categoryDAO.add(category));

		category =new Category();
		category.setName("Television");
		category.setDescription("This is some description for Television");
		category.setImageURL("CAT_2.png");
		
		assertEquals("Something went wrong while inserting a new Category", true,categoryDAO.add(category));

		//fetching and updating Category
		category = categoryDAO.get(2);
        category.setName("Tv");
		assertEquals("Something went wrong while updating the existing record",true, categoryDAO.update(category));
		
		//delete the category
		assertEquals("Something went wrong while deleting the existing record",true, categoryDAO.delete(category));
		
		//fetching the list
		assertEquals("Something went wrong while fetching the list of products",1, categoryDAO.list().size());
	}
	
}
