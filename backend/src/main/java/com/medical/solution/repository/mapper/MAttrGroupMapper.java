package com.medical.solution.repository.mapper;

import com.medical.solution.entity.MAttrGroup;;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MAttrGroupMapper implements RowMapper<MAttrGroup> {
    @Override
    public MAttrGroup mapRow(ResultSet resultSet, int i) throws SQLException {
        return new MAttrGroup(resultSet.getLong("attr_group_id"), resultSet.getString("name"),
                resultSet.getString("subgroup"));
    }
}
