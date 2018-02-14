package com.medical.solution.service.i;

import com.medical.solution.entity.AttrGroup;

import java.util.List;

public interface AttrGroupService extends AbstractDataService<AttrGroup> {
    List<AttrGroup> findAllByName(String name);
}
