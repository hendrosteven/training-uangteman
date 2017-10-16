package com.uangteman.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.uangteman.dao.CategoryDao;
import com.uangteman.model.Product;

public class ProductMapper implements RowMapper<Product>{
	
	private CategoryDao cDao;
	
	public ProductMapper(CategoryDao cDao) {
		this.cDao = cDao;
	}

	@Override
	public Product mapRow(ResultSet rs, int index) throws SQLException {
		Product product = new Product();
		product.setId(rs.getInt("id"));
		product.setName(rs.getString("name"));
		product.setDescription(rs.getString("description"));
		product.setPrice(rs.getDouble("price"));
		product.setCategory(cDao.findById(rs.getInt("category_id")));
		return product;
	}

}
