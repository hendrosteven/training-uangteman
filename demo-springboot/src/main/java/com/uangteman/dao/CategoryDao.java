package com.uangteman.dao;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.uangteman.dao.mapper.CategoryMapper;
import com.uangteman.model.Category;

@Repository
public class CategoryDao {

    @Autowired
    private JdbcTemplate template;

    public int insert(Category category) {
        return this.template.update("insert into tcategory(name) values(?)", category.getName());
    }

    public int update(Category category) {
        return this.template.update("update tcategory set name=? where " + "id=?", category.getName(),
                category.getId());
    }

    public List<Category> findAll() {
        return this.template.query("select id,name from tcategory", 
        		new CategoryMapper());
    }

    public Category findById(int id) {
        return this.template.queryForObject("select id,name from tcategory " + "where id=?", new Object[]{id},
                new CategoryMapper());
    }
}
