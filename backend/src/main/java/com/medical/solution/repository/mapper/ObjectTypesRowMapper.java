package com.medical.solution.repository.mapper;

import com.medical.solution.entity.ObjectType;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ObjectTypesRowMapper implements RowMapper<ObjectType> {
    @Override
    public ObjectType mapRow(ResultSet resultSet, int i) throws SQLException {
        return new ObjectType(resultSet.getLong("object_type_id"), resultSet.getString("name"),
                resultSet.getLong("parent_id"));
    }
}
