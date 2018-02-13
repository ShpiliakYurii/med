package com.medical.solution.repository;

import com.medical.solution.entity.MAttribute;
import com.medical.solution.repository.i.MAttributeRepository;
import com.medical.solution.repository.mapper.MAttrGroupMapper;
import com.medical.solution.repository.mapper.MAttributeMapper;
import com.medical.solution.utils.DBHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;


@Repository
public class MAttributeRepositoryImpl extends AbstractRepositoryImpl<MAttribute> implements MAttributeRepository {

    private final static String FIND_ALL_SQL = "SELECT * FROM m_attributes";
    private final static String FIND_BY_ID_SQL = "SELECT * FROM m_attributes WHERE attr_id=?";
    private final static String INSERT_SQL = "INSERT INTO m_attributes(name, attr_id, attr_type_id, attr_group_id) VALUES(?,?,?,?)";
    private final static String UPDATE_SQL = "UPDATE m_attributes SET name = ?, attr_type_id = ?, attr_group_id = ? WHERE attr_id = ?";
    private final static String DELETE_SQL = "DELETE FROM m_attributes WHERE attr_id = ?";

    @Autowired
    public MAttributeRepositoryImpl(JdbcTemplate jdbcTemplate) {
        super(jdbcTemplate);
    }

    @Override
    public MAttribute findById(long id) {
        return getJdbcTemplate().queryForObject(FIND_BY_ID_SQL, new Object[]{id}, new MAttributeMapper());
    }

    @Override
    public MAttribute create(MAttribute attr) {
        attr.setAttrId(DBHelper.getId());
        KeyHolder holder = new GeneratedKeyHolder();
        getJdbcTemplate().update(connection -> {
            PreparedStatement ps = connection.prepareStatement(INSERT_SQL);
            ps.setString(1, attr.getName());
            ps.setLong(2, attr.getAttrId());
            ps.setInt(3, attr.getAttrTypeId());
            ps.setLong(4, attr.getAttrGroupId());
            return ps;
        }, holder);

        return attr;
    }

    @Override
    public MAttribute update(MAttribute attr) {
        KeyHolder holder = new GeneratedKeyHolder();
        getJdbcTemplate().update(connection -> {
            PreparedStatement ps = connection.prepareStatement(UPDATE_SQL);
            ps.setString(1, attr.getName());
            ps.setInt(2, attr.getAttrTypeId());
            ps.setLong(3, attr.getAttrGroupId());
            ps.setLong(4, attr.getAttrId());
            return ps;
        }, holder);

        return attr;
    }

    @Override
    public String getFindAllQuery() {
        return FIND_ALL_SQL;
    }

    @Override
    public String getDeleteQuery() {
        return DELETE_SQL;
    }

    @Override
    public RowMapper getRowMapper() {
        return new MAttributeMapper();
    }
}
