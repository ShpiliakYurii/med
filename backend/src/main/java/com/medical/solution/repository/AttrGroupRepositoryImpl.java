package com.medical.solution.repository;

import com.medical.solution.entity.AttrGroup;
import com.medical.solution.repository.i.AttrGroupRepository;
import com.medical.solution.repository.mapper.AttrGroupMapper;
import com.medical.solution.utils.DBHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;

@Repository
public class AttrGroupRepositoryImpl extends AbstractRepositoryImpl<AttrGroup> implements AttrGroupRepository {

    private final static String FIND_ALL_SQL = "SELECT * FROM m_attr_groups order by name";
    private final static String FIND_BY_ID_SQL = "SELECT * FROM m_attr_groups WHERE attr_group_id=?";
    private final static String INSERT_SQL = "INSERT INTO m_attr_groups(attr_group_id, name, subgroup) VALUES(?,?,?)";
    private final static String UPDATE_SQL = "UPDATE m_attr_groups SET name = ?, subgroup = ? WHERE attr_group_id = ?";
    private final static String DELETE_SQL = "DELETE FROM m_attr_groups WHERE attr_group_id = ?";

    @Autowired
    public AttrGroupRepositoryImpl(JdbcTemplate jdbcTemplate) {
        super(jdbcTemplate);
    }

    @Override
    public AttrGroup findById(long id) {
        return getJdbcTemplate().queryForObject(FIND_BY_ID_SQL, new Object[]{id}, new AttrGroupMapper());
    }

    @Override
    public AttrGroup create(AttrGroup attrGroup) {
        attrGroup.setAttrGroupId(DBHelper.getId());
        KeyHolder holder = new GeneratedKeyHolder();
        getJdbcTemplate().update(connection -> {
            PreparedStatement ps = connection.prepareStatement(INSERT_SQL);
            ps.setLong(1, attrGroup.getAttrGroupId());
            ps.setString(2, attrGroup.getName());
            ps.setString(3, attrGroup.getSubgroup());
            return ps;
        }, holder);

        return attrGroup;
    }

    @Override
    public AttrGroup update(AttrGroup attrGroup) {
        KeyHolder holder = new GeneratedKeyHolder();
        getJdbcTemplate().update(connection -> {
            PreparedStatement ps = connection.prepareStatement(UPDATE_SQL);
            ps.setString(1, attrGroup.getName());
            ps.setString(2, attrGroup.getSubgroup());
            ps.setLong(3, attrGroup.getAttrGroupId());
            return ps;
        }, holder);

        return attrGroup;
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
        return new AttrGroupMapper();
    }


}
