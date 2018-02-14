package com.medical.solution.repository.mapper;

import com.medical.solution.entity.AttrGroup;;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AttrGroupRowMapper implements RowMapper<AttrGroup> {
    @Override
    public AttrGroup mapRow(ResultSet resultSet, int i) throws SQLException {
        return new AttrGroup(resultSet.getLong("attr_group_id"), resultSet.getString("name"),
                resultSet.getString("subgroup"));
    }
}
