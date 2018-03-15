package com.medical.solution.repository;

import com.medical.solution.entity.Attribute;
import com.medical.solution.repository.i.AttributeRepository;
import com.medical.solution.repository.mapper.AttributeRowMapper;
import com.medical.solution.utils.DBHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;


@Repository
public class AttributeRepositoryImpl extends AbstractRepositoryImpl<Attribute> implements AttributeRepository {

    private final static String FIND_ALL_SQL = "SELECT * FROM m_attributes";
    private final static String FIND_BY_ID_SQL = "SELECT * FROM m_attributes WHERE attr_id=?";
    private final static String INSERT_SQL = "INSERT INTO m_attributes(name, attr_id, attr_type_id, attr_group_id, attr_type_def_id) VALUES(?,?,?,?,?)";
    private final static String UPDATE_SQL = "UPDATE m_attributes SET name = ?, attr_type_id = ?, attr_group_id = ?, attr_type_def_id = ? WHERE attr_id = ?";
    private final static String DELETE_SQL = "DELETE FROM m_attributes WHERE attr_id = ?";

    @Autowired
    public AttributeRepositoryImpl(JdbcTemplate jdbcTemplate) {
        super(jdbcTemplate);
    }

    @Override
    public Attribute findById(long id) {
        return getJdbcTemplate().queryForObject(FIND_BY_ID_SQL, new Object[]{id}, new AttributeRowMapper());
    }

    @Override
    public Attribute create(Attribute attr) {
        attr.setAttrId(DBHelper.getId());
        KeyHolder holder = new GeneratedKeyHolder();
        getJdbcTemplate().update(connection -> {
            PreparedStatement ps = connection.prepareStatement(INSERT_SQL);
            ps.setString(1, attr.getName());
            ps.setLong(2, attr.getAttrId());
            ps.setInt(3, attr.getAttrTypeId());
            ps.setLong(4, attr.getAttrGroupId());
            if (attr.getAttrTypeDefId() != null)
                ps.setLong(5, attr.getAttrTypeDefId());
            else
                ps.setObject(5, null);
            return ps;
        }, holder);

        return attr;
    }

    @Override
    public Attribute update(Attribute attr) {
        KeyHolder holder = new GeneratedKeyHolder();
        getJdbcTemplate().update(connection -> {
            PreparedStatement ps = connection.prepareStatement(UPDATE_SQL);
            ps.setString(1, attr.getName());
            ps.setInt(2, attr.getAttrTypeId());
            ps.setLong(3, attr.getAttrGroupId());
            ps.setLong(4, attr.getAttrId());
            if (attr.getAttrTypeDefId() != null)
                ps.setLong(5, attr.getAttrTypeDefId());
            else
                ps.setObject(5, null);
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
        return new AttributeRowMapper();
    }
}
