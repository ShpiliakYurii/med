package com.medical.solution.repository.mapper;

import com.medical.solution.entity.Attribute;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AttributeMapper implements RowMapper<Attribute> {

    @Override
    public Attribute mapRow(ResultSet resultSet, int i) throws SQLException {
        return new Attribute(resultSet.getLong("attr_id"), resultSet.getString("name"),
                resultSet.getLong("attr_group_id"), resultSet.getInt("attr_type_id"));
    }
}
