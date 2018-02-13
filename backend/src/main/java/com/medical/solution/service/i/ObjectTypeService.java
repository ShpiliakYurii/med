package com.medical.solution.service.i;

import com.medical.solution.entity.MObjectType;
import org.springframework.stereotype.Service;

@Service
public interface ObjectTypeService extends AbstractDataService<MObjectType> {
    MObjectType getAllHierarchy();
}
