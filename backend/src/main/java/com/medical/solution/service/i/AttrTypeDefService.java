package com.medical.solution.service.i;

import com.medical.solution.entity.AttrTypeDef;

import java.util.List;

public interface AttrTypeDefService extends AbstractDataService<AttrTypeDef> {
    List<AttrTypeDef> findAllByName(String name);
}
