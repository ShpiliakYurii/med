package com.medical.solution.repository;

import com.medical.solution.entity.MObjectType;
import com.medical.solution.repository.i.MObjectTypesRepository;
import com.medical.solution.repository.mapper.MObjectTypesRowMapper;
import com.medical.solution.utils.DBHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.util.List;

@Repository
public class MObjectTypesRepositoryImpl extends AbstractRepositoryImpl<MObjectType> implements MObjectTypesRepository {

    private final static String FIND_ALL_SQL = "SELECT * FROM m_object_types";
    private final static String FIND_BY_ID_SQL = "SELECT * FROM m_object_types WHERE object_type_id=?";
    private final static String FIND__ALL_BY_PARENT_ID_SQL = "SELECT * FROM m_object_types WHERE parent_id=?";
    private final static String INSERT_SQL = "INSERT INTO m_object_types(object_type_id, name, parent_id) VALUES(?,?,?)";
    private final static String UPDATE_SQL = "UPDATE m_object_types SET name = ?, parent_id = ? WHERE object_type_id = ?";
    private final static String DELETE_SQL = "DELETE FROM m_object_types WHERE object_type_id = ?";


    @Autowired
    public MObjectTypesRepositoryImpl(JdbcTemplate jdbcTemplate) {
        super(jdbcTemplate);
    }


    @Transactional(readOnly = true)
    public MObjectType findById(long objectTypeId) {
        return getJdbcTemplate().queryForObject(FIND_BY_ID_SQL, new Object[]{objectTypeId}, new MObjectTypesRowMapper());
    }

    public MObjectType create(final MObjectType objectType) {
        objectType.setObjectTypeId(DBHelper.getId());
        KeyHolder holder = new GeneratedKeyHolder();
        getJdbcTemplate().update(connection -> {
            PreparedStatement ps = connection.prepareStatement(INSERT_SQL);
            ps.setLong(1, objectType.getObjectTypeId());
            ps.setString(2, objectType.getName());
            ps.setLong(3, objectType.getParentId());
            return ps;
        }, holder);

        return objectType;
    }

    @Override
    public MObjectType update(MObjectType objectType) {
        KeyHolder holder = new GeneratedKeyHolder();
        getJdbcTemplate().update(connection -> {
            PreparedStatement ps = connection.prepareStatement(UPDATE_SQL);
            ps.setString(1, objectType.getName());
            ps.setLong(2, objectType.getParentId());
            ps.setLong(3, objectType.getObjectTypeId());
            return ps;
        }, holder);

        return objectType;
    }


    public List<MObjectType> findAllByParentId(Long parentId) {
        return getJdbcTemplate().query(FIND__ALL_BY_PARENT_ID_SQL, new Object[]{parentId}, new MObjectTypesRowMapper());
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
        return new MObjectTypesRowMapper();
    }

}
