package com.medical.solution.service;

import com.medical.solution.entity.AttrGroup;
import com.medical.solution.repository.i.AttrGroupRepository;
import com.medical.solution.service.i.AttrGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AttrGroupServiceImpl implements AttrGroupService {

    private AttrGroupRepository attrGroupRepository;

    @Autowired
    public AttrGroupServiceImpl(AttrGroupRepository attrGroupRepository) {
        this.attrGroupRepository = attrGroupRepository;
    }

    @Override
    public AttrGroup add(AttrGroup o) {
        return attrGroupRepository.create(o);
    }

    @Override
    public AttrGroup update(AttrGroup o) {
        return attrGroupRepository.update(o);
    }

    @Override
    public AttrGroup getById(long id) throws NoSuchMethodException {
        return attrGroupRepository.findById(id);
    }

    @Override
    public void delete(long id) throws NoSuchMethodException {
        attrGroupRepository.delete(id);
    }

    @Override
    public List<AttrGroup> getAll() {
        return attrGroupRepository.findAll();
    }

    @Override
    public List<AttrGroup> findAllByName(String name){
        return attrGroupRepository.findAllByName(name);
    }
}
