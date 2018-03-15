package com.medical.solution.repository;

import com.medical.solution.entity.ListValue;
import com.medical.solution.repository.i.ListValueRepository;
import com.medical.solution.repository.mapper.AttrTypeDefRowMapper;
import com.medical.solution.repository.mapper.ListValueRowMapper;
import com.medical.solution.utils.DBHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.List;

@Repository
public class ListValueRepositoryImpl extends AbstractRepositoryImpl<ListValue> implements ListValueRepository {

    private final static String FIND_ALL_SQL = "SELECT * FROM m_list_values";
    private final static String FIND_BY_ID_SQL = "SELECT * FROM m_list_values WHERE list_value_id = ?";
    private final static String INSERT_SQL = "INSERT INTO m_list_values(list_value_id, attr_type_def_id, value) VALUES(?,?,?)";
    private final static String UPDATE_SQL = "UPDATE m_list_values SET value = ? WHERE list_value_id = ?";
    private final static String DELETE_SQL = "DELETE FROM m_list_values WHERE list_value_id = ?";

    @Autowired
    public ListValueRepositoryImpl(JdbcTemplate jdbcTemplate) {
        super(jdbcTemplate);
    }

    @Override
    public String getDeleteQuery() {
        return DELETE_SQL;
    }

    @Override
    public String getFindAllQuery() {
        return FIND_ALL_SQL;
    }

    @Override
    public RowMapper getRowMapper() {
        return new ListValueRowMapper();
    }

    @Override
    public ListValue findById(long id) {
        return getJdbcTemplate().queryForObject(FIND_BY_ID_SQL, new Object[]{id}, new ListValueRowMapper());
    }


    @Override
    public ListValue create(ListValue lv) {
        lv.setListValueId(DBHelper.getId());
        KeyHolder holder = new GeneratedKeyHolder();
        getJdbcTemplate().update(connection -> {
            PreparedStatement ps = connection.prepareStatement(INSERT_SQL);
            ps.setLong(1, lv.getListValueId());
            ps.setLong(2, lv.getAttrTypeDefId());
            ps.setString(3, lv.getValue());
            return ps;
        }, holder);

        return lv;
    }

    @Override
    public ListValue update(ListValue lv) {
        KeyHolder holder = new GeneratedKeyHolder();
        getJdbcTemplate().update(connection -> {
            PreparedStatement ps = connection.prepareStatement(UPDATE_SQL);
            ps.setString(1, lv.getValue());
            ps.setLong(2, lv.getListValueId());
            return ps;
        }, holder);

        return lv;
    }
}
