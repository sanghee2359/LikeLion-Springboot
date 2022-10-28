package com.springboot.hello.dao;


import com.springboot.hello.domain.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
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
    // create
    public void add(User user) {

        this.jdbcTemplate.update("INSERT INTO users(id, name, password) VALUES(?, ?, ?);",
                user.getId(), user.getName(), user.getPassword());

    }
    // read
    public User findById(String id)  {
        String sql = "SELECT id, name, password FROM users WHERE id=?;";
        return this.jdbcTemplate.queryForObject(sql, rowMapper, id);
    }
    public List<User> getAll(){
        String sql = "SELECT * from users order by id";
        return this.jdbcTemplate.query(sql, rowMapper);
    }
    // update
    public int update(User user, String id) {
        String sql = "UPDATE users SET name = ?, password = ? WHERE id = ?;";

        return jdbcTemplate.update(sql,rowMapper, id);
    }
    // delete
    public void deleteAll() {
        this.jdbcTemplate.update("DELETE FROM users");
    }
}
