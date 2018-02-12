package com.medical.solution.repository.mapper;

import com.medical.solution.entity.MObjectType;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MObjectTypesRowMapper implements RowMapper<MObjectType> {
    @Override
    public MObjectType mapRow(ResultSet resultSet, int i) throws SQLException {
        return new MObjectType(resultSet.getLong("object_type_id"), resultSet.getString("name"),
                resultSet.getLong("parent_id"));
    }
}
