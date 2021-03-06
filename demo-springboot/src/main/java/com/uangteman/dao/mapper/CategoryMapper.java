package com.uangteman.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import com.uangteman.model.Category;

public class CategoryMapper implements RowMapper<Category> {

	@Override
	public Category mapRow(ResultSet rs, int index) throws SQLException {
		 Category category = new Category();
         category.setId(rs.getInt("id"));
         category.setName(rs.getString("name"));
         return category;
	}

}
