package com.medical.solution.repository.i;

import com.medical.solution.entity.AttrGroup;

import java.util.List;

public interface AttrGroupRepository extends AbstractRepository<AttrGroup> {
    List<AttrGroup> findAllByName(String name);
}
