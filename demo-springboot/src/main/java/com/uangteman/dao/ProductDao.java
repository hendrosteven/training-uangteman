package com.uangteman.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.uangteman.dao.mapper.ProductMapper;
import com.uangteman.model.Product;

@Repository
public class ProductDao {

	@Autowired
	private JdbcTemplate template;
	@Autowired
	private CategoryDao cDao;	

	public int insert(Product product) {
		return this.template.update("insert into tproduct" + 
				"(name, description,price,category_id) values(?,?,?,?)",
				product.getName(), product.getDescription(), 
				product.getPrice(), product.getCategory().getId());
	}
	
	public List<Product> findAll() {
		return this.template.query("select id,name,description," + 
					"price,category_id from tproduct",
				new ProductMapper(cDao));
	}
}
