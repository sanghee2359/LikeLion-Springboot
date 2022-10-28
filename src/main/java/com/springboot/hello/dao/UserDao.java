package com.springboot.hello.dao;


import com.springboot.hello.domain.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
@Component
public class UserDao {
    private final JdbcTemplate jdbcTemplate;
    private final DataSource dataSource;

    public UserDao(DataSource dataSource, JdbcTemplate jdbcTemplate) {
        this.dataSource = dataSource;
        this.jdbcTemplate = jdbcTemplate;
    }
    RowMapper<User> rowMapper = new RowMapper<User>() {
        @Override
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            User user = new User(rs.getString("id"), rs.getString("name"), rs.getString("password"));
            return user;
        }
    };

    public void deleteAll() {
        this.jdbcTemplate.update("DELETE FROM users");
    }
    public int getCount()  {
        return this.jdbcTemplate.queryForObject("SELECT count(*) FROM users;", Integer.class);
    }
    public void add(User user) {
//        Connection conn = connectionMaker.openConnection();
        this.jdbcTemplate.update("INSERT INTO users(id, name, password) VALUES(?, ?, ?);",
                user.getId(), user.getName(), user.getPassword());

    }
    public User findById(String id)  {
        String sql = "SELECT id, name, password FROM users WHERE id=?;";
        return this.jdbcTemplate.queryForObject(sql, rowMapper, id);
    }

    public List<User> getAll(){
        String sql = "SELECT * from users order by id";
        return this.jdbcTemplate.query(sql, rowMapper);
    }
}
