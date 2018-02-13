package com.medical.solution.service;

import com.medical.solution.entity.AttributeObjectType;
import com.medical.solution.repository.i.AttributeObjectTypeRepository;
import com.medical.solution.service.i.AttributeObjectTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AttributeObjectTypeServiceImpl implements AttributeObjectTypeService {

    private AttributeObjectTypeRepository attributeObjectTypeRepository;

    @Autowired
    public AttributeObjectTypeServiceImpl(AttributeObjectTypeRepository attributeObjectTypeRepository) {
        this.attributeObjectTypeRepository = attributeObjectTypeRepository;
    }

    @Override
    public void delete(long attrId, long objectTypeId) {
        attributeObjectTypeRepository.delete(attrId, objectTypeId);
    }

    @Override
    public AttributeObjectType add(AttributeObjectType o) {
        return attributeObjectTypeRepository.create(o);
    }

    @Override
    public AttributeObjectType update(AttributeObjectType o) {
        return attributeObjectTypeRepository.update(o);
    }

    @Override
    public AttributeObjectType getById(long id) throws NoSuchMethodException {
        throw new NoSuchMethodException("This method does not exist for AttributeObjectTypeService entity.");
    }

    @Override
    public void delete(long id) throws NoSuchMethodException {
        throw new NoSuchMethodException("This method does not exist for AttributeObjectTypeService entity.");
    }

    @Override
    public List<AttributeObjectType> getAll() {
        return attributeObjectTypeRepository.findAll();
    }
}
