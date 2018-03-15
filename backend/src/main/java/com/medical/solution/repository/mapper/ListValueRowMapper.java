package com.medical.solution.repository.mapper;

import com.medical.solution.entity.ListValue;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ListValueRowMapper implements RowMapper<ListValue> {
    @Override
    public ListValue mapRow(ResultSet resultSet, int i) throws SQLException {
        return new ListValue(resultSet.getLong("list_value_id"), resultSet.getLong("attr_type_def_id"),
                resultSet.getString("value"));
    }
}
