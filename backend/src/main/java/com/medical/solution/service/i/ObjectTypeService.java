package com.medical.solution.service.i;

import com.medical.solution.entity.ObjectType;
import org.springframework.stereotype.Service;

@Service
public interface ObjectTypeService extends AbstractDataService<ObjectType> {
    ObjectType getAllHierarchy() throws NoSuchMethodException;
}
