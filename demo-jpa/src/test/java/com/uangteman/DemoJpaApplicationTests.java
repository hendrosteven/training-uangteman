package com.uangteman;

import java.util.ArrayList;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.uangteman.entity.Category;
import com.uangteman.repo.CategoryRepo;

import junit.framework.Assert;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=DemoJpaApplication.class, 
webEnvironment=SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DemoJpaApplicationTests {
	
	@Autowired
	private CategoryRepo categoryRepo;	
	
	@Autowired
	private TestRestTemplate testRestTemplate;
	
	@LocalServerPort
	int port;

	
	@Test
	public void contextLoads() {
		Category category = new Category();
		category.setName("Test Category");
		
		Category result = categoryRepo.save(category);
		Assert.assertEquals(category.getName(), result.getName());
		
		categoryRepo.delete(result);
		
		Assert.assertEquals(null, categoryRepo.findOne(result.getId()));
	}
	
	@Test
	public void testGetCategory(){		
		ResponseEntity<ArrayList> entity = this.testRestTemplate
				.withBasicAuth("bb@gmail.com", "827ccb0eea8a706c4c34a16891f84e7b")
				.getForEntity("http://localhost:"+this.port+"/category", 
						ArrayList.class);
		
		
		Assert.assertEquals(HttpStatus.OK, entity.getStatusCode());
	}

}
