package com.medical.solution.repository;

import com.medical.solution.repository.i.AbstractRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.util.List;

public abstract class AbstractRepositoryImpl<T> implements AbstractRepository<T>{
    private JdbcTemplate jdbcTemplate;

    public AbstractRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

//    @Override
    public void delete(long id) throws NoSuchMethodException {
        getJdbcTemplate().update(connection -> {
            PreparedStatement ps = connection.prepareStatement(getDeleteQuery());
            ps.setLong(1, id);
            return ps;
        });
    }

    @Transactional(readOnly = true)
    public List<T> findAll() {
        return getJdbcTemplate().query(getFindAllQuery(), getRowMapper());
    }

    public abstract String getDeleteQuery();

    public abstract String getFindAllQuery();

    public abstract RowMapper getRowMapper();
}
