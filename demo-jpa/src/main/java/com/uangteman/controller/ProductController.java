package com.uangteman.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.uangteman.dto.SearchNameForm;
import com.uangteman.entity.Product;
import com.uangteman.repo.ProductRepo;

@RestController
@RequestMapping("/product")
public class ProductController {

	@Autowired
	private ProductRepo repo;	
	
	@RequestMapping(method=RequestMethod.POST)
	public Product saveOrUpdate(@RequestBody Product product){
		return repo.save(product);		
	} 
	
	@RequestMapping(method=RequestMethod.GET)
	public List<Product> findAllProduct(){			
		return repo.findAllProduct();
	}
	
	@RequestMapping(method=RequestMethod.DELETE, value="/{id}")
	public boolean removeByid(@PathVariable("id") Long id){
		repo.delete(id);
		return true;
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/category/{categoryId}")
	public List<Product> findByCategoryId(@PathVariable("categoryId")
			Long categoryId){
		return repo.findByCategoryId(categoryId);
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/name")
	public List<Product> findByName(@RequestBody SearchNameForm form){			
		return repo.findByName("%"+form.getName()+"%");
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/price/{min}/{max}")
	public List<Product> findByPriceRange(@PathVariable("min") double min,
			@PathVariable("max") double max){
		return repo.findByPriceRange(min, max);		
	}
}
