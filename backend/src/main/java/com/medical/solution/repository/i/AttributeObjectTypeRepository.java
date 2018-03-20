package com.medical.solution.repository.i;

import com.medical.solution.entity.AttributeObjectType;

import java.util.List;
import java.util.Map;

public interface AttributeObjectTypeRepository extends AbstractRepository<AttributeObjectType> {
    void delete(long attrId, long objectTypeId);
    List<Map<String, Object>> findAllByOT(long otId);
}
