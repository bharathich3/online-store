package com.spring.storebackend.daoimpl;

import java.util.ArrayList;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.spring.storebackend.dao.CategoryDAO;
import com.spring.storebackend.dto.Category;

@Repository("categoryDAO")
@Transactional
public class CategoryDAOImpl implements CategoryDAO {

	@Autowired
	private SessionFactory sessionFactory;

	private static List<Category> categories = new ArrayList<>();

	// getting single category based on ID
	@Override
	public Category get(int id) {

		return sessionFactory.getCurrentSession().get(Category.class, Integer.valueOf(id));
	}

	@Override
	public List<Category> list() {
		 String selectActiveCategory = "FROM Category WHERE active = :active";
		 Query query=sessionFactory.getCurrentSession().createQuery(selectActiveCategory);
		 query.setParameter("active", true);
		
		return query.getResultList();
	}

	@Override
	public boolean add(Category category) {

		try {

			// add the category to the database table

			sessionFactory.getCurrentSession().persist(category);
			return true;

		} catch (Exception ex) {

			ex.printStackTrace();
			return false;
		}

	}

	/*
	 * Updating Single Category
	 */
	@Override
	public boolean update(Category category) {

		try {

			// update the category in the database table

			sessionFactory.getCurrentSession().update(category);
			;
			return true;

		} catch (Exception ex) {

			ex.printStackTrace();
			return false;
		}

	}

	/*
	 * Deleting cateogory
	 */
	@Override
	public boolean delete(Category category) {
		category.setActive(false);

		try {

			// delete the category in the database table

			sessionFactory.getCurrentSession().update(category);
			;
			return true;

		} catch (Exception ex) {

			ex.printStackTrace();
			return false;
		}

	}

//	static {
//		Category category = new Category();
//
//		// adding first Category
//		category.setId(1);
//		category.setName("Television");
//		category.setDescription("This is some description for Television");
//		category.setImageURL("CAT_1.png");
//		categories.add(category);
//
//		// adding second Category
//
//		category = new Category();
//
//		category.setId(2);
//		category.setName("Mobile");
//		category.setDescription("This is some description for Mobile");
//		category.setImageURL("CAT_2.png");
//		categories.add(category);
//		
//		// adding thrid Category
//
//		category = new Category();
//
//		category.setId(3);
//		category.setName("Tab");
//		category.setDescription("This is some description for Tab");
//		category.setImageURL("CAT_3.png");
//		categories.add(category);
//	}

}
