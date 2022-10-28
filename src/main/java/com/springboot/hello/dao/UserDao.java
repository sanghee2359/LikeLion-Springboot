package com.springboot.hello.dao;


import com.springboot.hello.domain.User;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

public class UserDao {
    private JdbcTemplate jdbcTemplate;
    private DataSource dataSource;

    public UserDao(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
    public void add(User user) {
        this.jdbcTemplate.update("INSERT users(id, name, password) VALUES (?, ?, ?),
                user.getId(), user.getName(), user.getPassword());
    }

}
