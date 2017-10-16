package com.uangteman.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.uangteman.dao.CategoryDao;
import com.uangteman.model.Category;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/category")
public class CategoryController {
	
	@Autowired
	private CategoryDao dao;

	@RequestMapping(method=RequestMethod.POST)
	public int saveCategory(@RequestBody Category category){
		return dao.insert(category);	
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public List<Category> findAll(){
		return dao.findAll();
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/{id}")
	public Category findById(@PathVariable("id") int id){
		return dao.findById(id);
	}
}
