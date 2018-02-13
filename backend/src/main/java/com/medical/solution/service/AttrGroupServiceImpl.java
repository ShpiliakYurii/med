package com.medical.solution.service;

import com.medical.solution.entity.MAttrGroup;
import com.medical.solution.repository.i.MAttrGroupRepository;
import com.medical.solution.service.i.AttrGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AttrGroupServiceImpl implements AttrGroupService {

    private MAttrGroupRepository mAttrGroupRepository;

    @Autowired
    public AttrGroupServiceImpl(MAttrGroupRepository mAttrGroupRepository) {
        this.mAttrGroupRepository = mAttrGroupRepository;
    }

    @Override
    public MAttrGroup add(MAttrGroup o) {
        return mAttrGroupRepository.create(o);
    }

    @Override
    public MAttrGroup update(MAttrGroup o) {
        return mAttrGroupRepository.update(o);
    }

    @Override
    public MAttrGroup getById(long id) {
        return mAttrGroupRepository.findById(id);
    }

    @Override
    public void delete(long id) {
        mAttrGroupRepository.delete(id);
    }

    @Override
    public List<MAttrGroup> getAll() {
        return mAttrGroupRepository.findAll();
    }
}
