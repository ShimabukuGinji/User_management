package com.example.usermanagement.repository;

import com.example.usermanagement.entity.User;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.DataClassRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserRepository implements IUserRepository {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public List<User> findAll() {
        return jdbcTemplate.query("SELECT * FROM users ORDER BY id",
            new DataClassRowMapper<>(User.class));
    }

    @Override
    public User findLogin(String loginId, String loginPass) {
        var param = new MapSqlParameterSource();
        param.addValue("ID", loginId);
        param.addValue("pass", loginPass);
        var list = jdbcTemplate.query("SELECT * FROM users WHERE login_id = :ID AND password = :pass ORDER BY id;",
                param,
                new DataClassRowMapper<>(User.class));
        return list.isEmpty() ? null : list.get(0);
    }
}
