package com.medical.solution.service.i;

import com.medical.solution.entity.AttributeObjectType;

public interface AttributeObjectTypeService extends AbstractDataService<AttributeObjectType> {
    void delete(long attrId, long objectTypeId);
}
