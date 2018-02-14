package com.medical.solution.service;

import com.medical.solution.entity.AttrTypeDef;
import com.medical.solution.repository.i.AttrTypeDefRepository;
import com.medical.solution.service.i.AttrTypeDefService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AttrTypeDefServiceImpl implements AttrTypeDefService {

    private AttrTypeDefRepository attrTypeDefRepository;

    @Autowired
    public AttrTypeDefServiceImpl(AttrTypeDefRepository attrTypeDefRepository) {
        this.attrTypeDefRepository = attrTypeDefRepository;
    }


    @Override
    public AttrTypeDef add(AttrTypeDef o) {
        return attrTypeDefRepository.create(o);
    }

    @Override
    public AttrTypeDef update(AttrTypeDef o) {
        return attrTypeDefRepository.update(o);
    }

    @Override
    public AttrTypeDef getById(long id) throws NoSuchMethodException {
        return attrTypeDefRepository.findById(id);
    }

    @Override
    public void delete(long id) throws NoSuchMethodException {
        attrTypeDefRepository.delete(id);
    }

    @Override
    public List<AttrTypeDef> getAll() {
        return attrTypeDefRepository.findAll();
    }

    @Override
    public List<AttrTypeDef> findAllByName(String name) {
        return attrTypeDefRepository.findAllByName(name);
    }
}
