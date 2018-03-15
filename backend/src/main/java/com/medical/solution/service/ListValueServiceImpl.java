package com.medical.solution.service;

import com.medical.solution.entity.ListValue;
import com.medical.solution.repository.i.ListValueRepository;
import com.medical.solution.service.i.ListValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListValueServiceImpl implements ListValueService {

    private ListValueRepository listValueRepository;

    @Autowired
    public ListValueServiceImpl(ListValueRepository listValueRepository) {
        this.listValueRepository = listValueRepository;
    }

    @Override
    public ListValue add(ListValue o) {
        return listValueRepository.create(o);
    }

    @Override
    public ListValue update(ListValue o) {
        return listValueRepository.update(o);
    }

    @Override
    public ListValue getById(long id) throws NoSuchMethodException {
        return listValueRepository.findById(id);
    }

    @Override
    public void delete(long id) throws NoSuchMethodException {
        listValueRepository.delete(id);
    }

    @Override
    public List<ListValue> getAll() {
        return listValueRepository.findAll();
    }
}
