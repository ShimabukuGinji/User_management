package com.example.usermanagement.repository;

import com.example.usermanagement.entity.IUser;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.DataClassRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserRepository implements IUserRepository {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public List<IUser> findAll() {
        return jdbcTemplate.query("SELECT * FROM users ORDER BY id",
            new DataClassRowMapper<>(IUser.class));
    }
}
