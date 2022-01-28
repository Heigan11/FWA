package edu.school21.cinema.repositories;

import edu.school21.cinema.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.List;

//@Component
public class UserDAO {

    private final JdbcTemplate jdbcTemplate;

    public UserDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<User> showTable(){
        return jdbcTemplate.query("SELECT * FROM users", new BeanPropertyRowMapper<>(User.class));
//        return jdbcTemplate.query("SELECT * FROM users", new UserMapper());
    }

    public User findByEmail(String email) {
        return jdbcTemplate.query("SELECT * FROM users WHERE email=?", new Object[]{email}, new BeanPropertyRowMapper<>(User.class))
                .stream().findAny().orElse(null);
    }

    public void save(User user) {
        jdbcTemplate.update("INSERT INTO users VALUES(?, ?, ?, ?, ?)",
                user.getName(), user.getSurname(), user.getPhone(), user.getEmail(), user.getPassword());
    }

    public void update(String email, User updatedUser) {
        jdbcTemplate.update("UPDATE users SET name=?, surname=?, phone=?, email=?, password=? WHERE email=?", updatedUser.getName(),
                updatedUser.getSurname(), updatedUser.getPhone(), updatedUser.getEmail(), updatedUser.getPassword(), email);
    }

    public void delete(String email) {
        jdbcTemplate.update("DELETE FROM users WHERE email=?", email);
    }
}
