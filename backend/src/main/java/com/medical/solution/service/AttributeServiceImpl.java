package com.medical.solution.service;

import com.medical.solution.entity.Attribute;
import com.medical.solution.repository.i.AttributeRepository;
import com.medical.solution.service.i.AttributeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AttributeServiceImpl implements AttributeService {

    private AttributeRepository attributeRepository;

    @Autowired
    public AttributeServiceImpl(AttributeRepository attributeRepository) {
        this.attributeRepository = attributeRepository;
    }

    @Override
    public Attribute add(Attribute o) {
        return attributeRepository.create(o);
    }

    @Override
    public Attribute update(Attribute o) {
        return attributeRepository.update(o);
    }

    @Override
    public Attribute getById(long id) {
        return attributeRepository.findById(id);
    }

    @Override
    public void delete(long id) {
        attributeRepository.delete(id);
    }

    @Override
    public List<Attribute> getAll() {
        return attributeRepository.findAll();
    }
}
