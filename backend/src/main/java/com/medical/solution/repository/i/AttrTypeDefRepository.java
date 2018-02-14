package com.medical.solution.repository.i;

import com.medical.solution.entity.AttrTypeDef;

import java.util.List;

public interface AttrTypeDefRepository extends AbstractRepository<AttrTypeDef> {
    List<AttrTypeDef> findAllByName(String name);
}
