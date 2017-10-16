package com.uangteman.dao;


import com.uangteman.model.Category;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

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
        return this.template.query("select id,name from tcategory", new RowMapper<Category>() {
            @Override
            public Category mapRow(ResultSet rs, int index) throws SQLException {
                Category category = new Category();
                category.setId(rs.getInt("id"));
                category.setName(rs.getString("name"));
                return category;
            }

        });
    }

    public Category findById(int id) {
        return this.template.queryForObject("select id,name from tcategory " + "where id=?", new Object[]{id},
                new RowMapper<Category>() {
            @Override
            public Category mapRow(ResultSet rs, int index) throws SQLException {
                Category category = new Category();
                category.setId(rs.getInt("id"));
                category.setName(rs.getString("name"));
                return category;
            }

        });
    }
}
