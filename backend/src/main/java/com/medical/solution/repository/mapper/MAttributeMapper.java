package com.medical.solution.repository.mapper;

import com.medical.solution.entity.MAttribute;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MAttributeMapper implements RowMapper<MAttribute> {

    @Override
    public MAttribute mapRow(ResultSet resultSet, int i) throws SQLException {
        return new MAttribute(resultSet.getLong("attr_id"), resultSet.getString("name"),
                resultSet.getLong("attr_group_id"), resultSet.getInt("attr_type_id"));
    }
}
