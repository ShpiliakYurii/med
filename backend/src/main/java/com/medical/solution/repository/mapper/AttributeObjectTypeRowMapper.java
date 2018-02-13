package com.medical.solution.repository.mapper;

import com.medical.solution.entity.AttributeObjectType;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AttributeObjectTypeRowMapper implements RowMapper<AttributeObjectType> {
    @Override
    public AttributeObjectType mapRow(ResultSet resultSet, int i) throws SQLException {
        return new AttributeObjectType(resultSet.getLong("attr_id"), resultSet.getLong("object_type_id"),
                resultSet.getInt("options"));
    }
}
