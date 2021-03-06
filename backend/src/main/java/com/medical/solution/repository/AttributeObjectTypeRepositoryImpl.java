package com.medical.solution.repository;

import com.medical.solution.entity.AttributeObjectType;
import com.medical.solution.repository.i.AttributeObjectTypeRepository;
import com.medical.solution.repository.mapper.AOTViewRowMapper;
import com.medical.solution.repository.mapper.AttrGroupRowMapper;
import com.medical.solution.repository.mapper.AttributeObjectTypeRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Map;

@Repository
public class AttributeObjectTypeRepositoryImpl extends AbstractRepositoryImpl<AttributeObjectType> implements AttributeObjectTypeRepository {

    private final static String FIND_ALL_SQL = "SELECT * FROM attribute_object_type";
    private final static String FIND_BY_ID_SQL = "SELECT * FROM attribute_object_type WHERE object_type_id = ?";
    private final static String FIND_BY_OT_ID_SQL = "SELECT aot.attr_id, aot.options, attr.name attr_name, attr.attr_type_id," +
            " attr_group.attr_group_id, attr_group.name attr_group_name " +
            "FROM attribute_object_type aot, m_attributes attr, m_attr_groups attr_group  " +
            "WHERE object_type_id = ? AND aot.attr_id = attr.attr_id AND attr.attr_group_id = attr_group.attr_group_id";
    private final static String INSERT_SQL = "INSERT INTO attribute_object_type(attr_id, object_type_id, options) VALUES(?,?,?)";
    private final static String UPDATE_SQL = "UPDATE attribute_object_type SET options = ? WHERE attr_id = ? AND object_type_id = ? ";
    private final static String DELETE_SQL = "DELETE FROM attribute_object_type WHERE attr_id = ? and object_type_id = ?";

    @Autowired
    public AttributeObjectTypeRepositoryImpl(JdbcTemplate jdbcTemplate) {
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
        return new AttributeObjectTypeRowMapper();
    }

    @Override
    public AttributeObjectType findById(long id) throws NoSuchMethodException {
        throw new NoSuchMethodException("This method does not exist for AttributeObjectTypeService entity.");
    }

    @Override
    public AttributeObjectType create(AttributeObjectType aot) {
        KeyHolder holder = new GeneratedKeyHolder();
        getJdbcTemplate().update(connection -> {
            PreparedStatement ps = connection.prepareStatement(INSERT_SQL);
            ps.setLong(1, aot.getAttrId());
            ps.setLong(2, aot.getObjectTypeId());
            ps.setInt(3, aot.getOptions());
            return ps;
        }, holder);

        return aot;
    }

    @Override
    public AttributeObjectType update(AttributeObjectType aot) {
        KeyHolder holder = new GeneratedKeyHolder();
        getJdbcTemplate().update(connection -> {
            PreparedStatement ps = connection.prepareStatement(UPDATE_SQL);
            ps.setInt(1, aot.getOptions());
            ps.setLong(2, aot.getAttrId());
            ps.setLong(3, aot.getObjectTypeId());
            return ps;
        }, holder);

        return aot;
    }

    @Override
    public void delete(long id) throws NoSuchMethodException {
        throw new NoSuchMethodException("This method does not exist for AttributeObjectTypeService entity.");
    }

    @Override
    public void delete(long attrId, long objectTypeId) {
        getJdbcTemplate().update(connection -> {
            PreparedStatement ps = connection.prepareStatement(getDeleteQuery());
            ps.setLong(1, attrId);
            ps.setLong(2, objectTypeId);
            return ps;
        });
    }

    @Override
    public List<Map<String, Object>> findAllByOT(long otId) {
        return getJdbcTemplate().query(FIND_BY_OT_ID_SQL, new Object[]{otId}, new AOTViewRowMapper());
    }


}
