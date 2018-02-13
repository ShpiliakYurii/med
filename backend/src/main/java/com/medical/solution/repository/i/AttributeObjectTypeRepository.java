package com.medical.solution.repository.i;

import com.medical.solution.entity.AttributeObjectType;

public interface AttributeObjectTypeRepository extends AbstractRepository<AttributeObjectType> {
    void delete(long attrId, long objectTypeId);
}
