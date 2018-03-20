package com.medical.solution.repository.mapper;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class AOTViewRowMapper implements RowMapper<Map<String, Object>> {

    @Override
    public Map<String, Object> mapRow(ResultSet resultSet, int i) throws SQLException {
        Map<String, Object> result = new HashMap<>();
        result.put("attrId", resultSet.getLong("attr_id")+"");
        result.put("options", resultSet.getInt("options"));
        result.put("attrName", resultSet.getString("attr_name"));
        result.put("attrTypeId", resultSet.getInt("attr_type_id"));
        result.put("attrGroupId", resultSet.getLong("attr_group_id")+"");
        result.put("attrGroupName", resultSet.getString("attr_group_name"));
        result.put("show", true);
        return result;
    }
}
