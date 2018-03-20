package com.medical.solution.service.i;

import com.medical.solution.entity.AttributeObjectType;

import java.util.Map;


public interface AttributeObjectTypeService extends AbstractDataService<AttributeObjectType> {
    void delete(long attrId, long objectTypeId);
    Map findAllByOT(long otId);
}
