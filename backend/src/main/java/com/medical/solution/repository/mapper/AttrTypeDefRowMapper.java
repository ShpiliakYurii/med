package com.medical.solution.repository.mapper;

import com.medical.solution.entity.AttrTypeDef;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AttrTypeDefRowMapper implements RowMapper<AttrTypeDef> {

    @Override
    public AttrTypeDef mapRow(ResultSet resultSet, int i) throws SQLException {
        return new AttrTypeDef(resultSet.getLong("attr_type_def_id"), resultSet.getInt("attr_type_id"),
                resultSet.getString("name"));
    }
}
