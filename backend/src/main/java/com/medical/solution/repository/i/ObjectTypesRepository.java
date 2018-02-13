package com.medical.solution.repository.i;

import com.medical.solution.entity.ObjectType;

import java.util.List;

public interface ObjectTypesRepository extends AbstractRepository<ObjectType> {
    List<ObjectType> findAllByParentId(Long id);

}
