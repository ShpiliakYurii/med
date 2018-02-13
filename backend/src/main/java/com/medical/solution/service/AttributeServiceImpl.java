package com.medical.solution.service;

import com.medical.solution.entity.MAttribute;
import com.medical.solution.repository.i.MAttributeRepository;
import com.medical.solution.service.i.AttributeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AttributeServiceImpl implements AttributeService {

    private MAttributeRepository mAttributeRepository;

    @Autowired
    public AttributeServiceImpl(MAttributeRepository mAttributeRepository) {
        this.mAttributeRepository = mAttributeRepository;
    }

    @Override
    public MAttribute add(MAttribute o) {
        return mAttributeRepository.create(o);
    }

    @Override
    public MAttribute update(MAttribute o) {
        return mAttributeRepository.update(o);
    }

    @Override
    public MAttribute getById(long id) {
        return mAttributeRepository.findById(id);
    }

    @Override
    public void delete(long id) {
        mAttributeRepository.delete(id);
    }

    @Override
    public List<MAttribute> getAll() {
        return mAttributeRepository.findAll();
    }
}
