package com.medical.solution.service;

import com.medical.solution.constants.DatabaseConstants;
import com.medical.solution.entity.ObjectType;
import com.medical.solution.repository.i.ObjectTypesRepository;
import com.medical.solution.service.i.ObjectTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ObjectTypeServiceImpl implements ObjectTypeService {

    private ObjectTypesRepository objectTypesRepository;

    @Autowired
    public ObjectTypeServiceImpl(ObjectTypesRepository objectTypesRepository) {
        this.objectTypesRepository = objectTypesRepository;
    }

    @Override
    public ObjectType add(ObjectType objectType) {
        return objectTypesRepository.create(objectType);
    }

    @Override
    public ObjectType update(ObjectType objectType) {
        return objectTypesRepository.update(objectType);
    }

    @Override
    public ObjectType getById(long id) {
        return objectTypesRepository.findById(id);
    }

    @Override
    public void delete(long id) {
        objectTypesRepository.delete(id);
    }

    @Override
    public List<ObjectType> getAll() {
        return objectTypesRepository.findAll();
    }

    @Override
    public ObjectType getAllHierarchy() {
        ObjectType root = objectTypesRepository.findById(DatabaseConstants.ROOT);
        getChildesHierarchy(root);
        return root;
    }

    private void getChildesHierarchy(ObjectType root) {
        root.setChildes(objectTypesRepository.findAllByParentId(root.getObjectTypeId()));
        root.getChildes().forEach(this::getChildesHierarchy);
    }

}
