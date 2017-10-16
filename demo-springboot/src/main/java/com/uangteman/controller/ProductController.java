package com.uangteman.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.uangteman.dao.ProductDao;
import com.uangteman.model.Product;

@RestController
@RequestMapping("/product")
public class ProductController {

	@Autowired
	private ProductDao dao;	
	
	@RequestMapping(method=RequestMethod.POST)
	public int saveProduct(@RequestBody Product product){
		return dao.insert(product);
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public List<Product> findAll(){
		//
		return dao.findAll();
	}
}
