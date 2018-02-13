package com.medical.solution.repository.i;

import com.medical.solution.entity.MObjectType;

import java.util.List;

public interface MObjectTypesRepository extends AbstractRepository<MObjectType> {
    List<MObjectType> findAllByParentId(Long id);

}
