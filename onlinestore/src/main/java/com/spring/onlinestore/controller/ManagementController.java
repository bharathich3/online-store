package com.spring.onlinestore.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.spring.onlinestore.util.FileUploadUtility;
import com.spring.onlinestore.validator.ProductValidator;
import com.spring.storebackend.dao.CategoryDAO;
import com.spring.storebackend.dao.ProductDAO;
import com.spring.storebackend.dto.Category;
import com.spring.storebackend.dto.Product;

@Controller
@RequestMapping("/manage")
public class ManagementController {
	
	@Autowired
	private CategoryDAO categoryDAO;
	@Autowired
	private ProductDAO productDAO;
	
	private static final Logger logger=LoggerFactory.getLogger(ManagementController.class);

	@RequestMapping(value = "/products",method =RequestMethod.GET)
	public ModelAndView showManageProducts(@RequestParam(name="operation",required = false) String operation) {
		ModelAndView mv=new ModelAndView("page");
		mv.addObject("userClickManageProducts",true);
		mv.addObject("title","Manage Products");
		//new Product
		Product nProduct=new Product();
		//set few of the fields
		nProduct.setSupplierId(1);
		nProduct.setActive(true);
		mv.addObject("product",nProduct);
		
		if(operation!=null) {
			if(operation.equals("product")) {
				mv.addObject("message","Product Submitted Successfully!");
			}else if (operation.equals("category")) {
				mv.addObject("message","Category Submitted Successfully!");

				
			}
		}
		return mv;
	}
	
	
	@RequestMapping(value = "/{id}/product",method = RequestMethod.GET)
	public ModelAndView showEditProduct(@PathVariable int id)
	{
		ModelAndView mv=new ModelAndView("page");
		mv.addObject("userClickManageProducts",true);
		mv.addObject("title","Manage Products");
		//fetch the Product fron=m datbase
		Product nProduct=productDAO.get(id);
		//set the product fetch from database
		mv.addObject("product",nProduct);
		return mv;
	}
	
	
	//Handling Product Submission
	@RequestMapping(value = "/products",method = RequestMethod.POST)
	public String handleProductSubmission(@Valid @ModelAttribute("product")Product mProduct,BindingResult results,Model model,HttpServletRequest request) {
		
		//handle Image validation for New Products
		if(mProduct.getId()==0) {
		new ProductValidator().validate(mProduct,results);
		}else {
			if(!mProduct.getFile().getOriginalFilename().equals("")) {
				new ProductValidator().validate(mProduct,results);

			}
		}
		//check if there are any errors
		if( results.hasErrors()) {
			model.addAttribute("userClickManageProducts",true);
			model.addAttribute("title","Manage Products");
			model.addAttribute("message","Validation Failed for Product Submission");
			return "page";
		}
		
		logger.info(mProduct.toString());
		
		if(mProduct.getId()==0) {
		//create a new product record
		productDAO.add(mProduct);
		}
		else {
			//updating the  Product if ID is not 0
          productDAO.update(mProduct);
		}
		if(!mProduct.getFile().getOriginalFilename().equals("")) {
			FileUploadUtility.uploadFile(request,mProduct.getFile(),mProduct.getCode());
		}
		
		
		
		return "redirect:/manage/products?operation=product";
	}
	
	@RequestMapping(value="/product/{id}/activation",method = RequestMethod.POST)
	@ResponseBody
	public String handleProductActivation(@PathVariable int id) {
		
		Product product=productDAO.get(id);
		boolean isActive=product.isActive();
		//activating and deactivating  based on the value of active field
		product.setActive(!product.isActive());
		//updating the product
		productDAO.update(product);
		
		return (isActive)?
				"You have Successfully deactivated the product with id"+product.getId()
		        : "You have Successfully activated the product with id"+product.getId();
				
				
	}
	
	
	//returning categories for all the request mapping
	@ModelAttribute("categories")
	public List<Category> getCategorries(){
		return categoryDAO.list();
		
	}
	//to handle Category Submission
	@RequestMapping(value = "/category",method = RequestMethod.POST)
	public String handlecategorySubmission(@ModelAttribute Category category) 
	{
		//add new Category
		categoryDAO.add(category);
		
		
		return "redirect:/manage/products?operation=category";
		
	}
	
	@ModelAttribute("category")
	public Category getCategory() {
		return new Category();
	}
	
}
