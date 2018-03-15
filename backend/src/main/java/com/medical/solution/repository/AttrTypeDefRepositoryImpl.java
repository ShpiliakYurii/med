package com.medical.solution.repository;

import com.medical.solution.entity.AttrTypeDef;
import com.medical.solution.repository.i.AttrTypeDefRepository;
import com.medical.solution.repository.mapper.AttrTypeDefRowMapper;
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
public class AttrTypeDefRepositoryImpl extends AbstractRepositoryImpl<AttrTypeDef> implements AttrTypeDefRepository {

    private final static String FIND_ALL_SQL = "SELECT * FROM m_attr_type_def";
    private final static String FIND_ALL_BY_NAME_SQL = "SELECT * FROM m_attr_type_def where lower(name) like CONCAT('%',lower(?),'%')";
    private final static String FIND_BY_ID_SQL = "SELECT * FROM m_attr_type_def WHERE m_attr_type_def = ?";
    private final static String INSERT_SQL = "INSERT INTO m_attr_type_def(name, attr_type_def_id, attr_type_id) VALUES(?,?,?)";
    private final static String UPDATE_SQL = "UPDATE m_attr_type_def SET name = ?, attr_type_id = ? WHERE m_attr_type_def = ?";
    private final static String DELETE_SQL = "DELETE FROM m_attr_type_def WHERE attr_type_def_id = ?";

    @Autowired
    public AttrTypeDefRepositoryImpl(JdbcTemplate jdbcTemplate) {
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
        return new AttrTypeDefRowMapper();
    }

    @Override
    public AttrTypeDef findById(long id){
        return getJdbcTemplate().queryForObject(FIND_BY_ID_SQL, new Object[]{id}, new AttrTypeDefRowMapper());
    }

    @Override
    public AttrTypeDef create(AttrTypeDef atd) {
        atd.setAttrTypeDefId(DBHelper.getId());
        KeyHolder holder = new GeneratedKeyHolder();
        getJdbcTemplate().update(connection -> {
            PreparedStatement ps = connection.prepareStatement(INSERT_SQL);
            ps.setString(1, atd.getName());
            ps.setLong(2, atd.getAttrTypeDefId());
            ps.setInt(3, atd.getAttrTypeId());
            return ps;
        }, holder);

        return atd;
    }

    @Override
    public AttrTypeDef update(AttrTypeDef atd) {
        KeyHolder holder = new GeneratedKeyHolder();
        getJdbcTemplate().update(connection -> {
            PreparedStatement ps = connection.prepareStatement(UPDATE_SQL);
            ps.setString(1, atd.getName());
            ps.setInt(2, atd.getAttrTypeId());
            ps.setLong(3, atd.getAttrTypeDefId());
            return ps;
        }, holder);

        return atd;
    }

    @Override
    public List<AttrTypeDef> findAllByName(String name){
        return getJdbcTemplate().query(FIND_ALL_BY_NAME_SQL, new Object[]{name}, new AttrTypeDefRowMapper());
    }

}
