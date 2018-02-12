package com.medical.solution.service;

import com.medical.solution.constants.DatabaseConstants;
import com.medical.solution.entity.MObjectType;
import com.medical.solution.repository.i.MObjectTypesRepository;
import com.medical.solution.service.i.ObjectTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ObjectTypeServiceImpl implements ObjectTypeService {

    private MObjectTypesRepository mObjectTypesRepository;

    @Autowired
    public ObjectTypeServiceImpl(MObjectTypesRepository mObjectTypesRepository) {
        this.mObjectTypesRepository = mObjectTypesRepository;
    }

    @Override
    public MObjectType add(MObjectType mObjectType) {
        return mObjectTypesRepository.create(mObjectType);
    }

    @Override
    public MObjectType update(MObjectType mObjectType) {
        return mObjectTypesRepository.update(mObjectType);
    }

    @Override
    public MObjectType getById(long id) {
        return mObjectTypesRepository.findById(id);
    }

    @Override
    public void delete(long id) {
        mObjectTypesRepository.delete(id);
    }

    @Override
    public List<MObjectType> getAll() {
        return mObjectTypesRepository.findAll();
    }

    @Override
    public MObjectType getAllHierarchy() {
        MObjectType root = mObjectTypesRepository.findById(DatabaseConstants.ROOT);
        getChildesHierarchy(root);
        return root;
    }

    private void getChildesHierarchy(MObjectType root) {
        root.setChildes(mObjectTypesRepository.findAllByParentId(root.getObjectTypeId()));
        root.getChildes().forEach(this::getChildesHierarchy);
    }

}
