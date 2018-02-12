package com.medical.solution.controller;

import com.medical.solution.entity.MObjectType;
import com.medical.solution.service.i.ObjectTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping(value = "/api/object-type")
@CrossOrigin(origins = "http://localhost:4200")
public class ObjectTypeController {

    private ObjectTypeService objectTypeService;

    @Autowired
    public ObjectTypeController(ObjectTypeService objectTypeService) {
        this.objectTypeService = objectTypeService;
    }

    @PutMapping
    public ResponseEntity<?> add(@RequestBody MObjectType mObjectType) {
        MObjectType res = objectTypeService.add(mObjectType);
        return new ResponseEntity<Object>(res, HttpStatus.CREATED);
    }

    @PostMapping
    public ResponseEntity<?> update(@RequestBody MObjectType mObjectType) {
        MObjectType res = objectTypeService.update(mObjectType);
        return new ResponseEntity<Object>(res, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> getAll() {
        return new ResponseEntity<Object>(objectTypeService.getAllHierarchy(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") long id) {
        objectTypeService.delete(id);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
}
