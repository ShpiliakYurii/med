package com.medical.solution.service.i;

import com.medical.solution.entity.MObjectType;

public interface ObjectTypeService extends AbstractDataService<MObjectType> {
    MObjectType getAllHierarchy();
}
