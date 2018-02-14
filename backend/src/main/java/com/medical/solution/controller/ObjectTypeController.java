package com.medical.solution.controller;

import com.medical.solution.entity.ObjectType;
import com.medical.solution.service.i.ObjectTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<?> add(@RequestBody ObjectType objectType) {
        ObjectType res = objectTypeService.add(objectType);
        return new ResponseEntity<Object>(res, HttpStatus.CREATED);
    }

    @PostMapping
    public ResponseEntity<?> update(@RequestBody ObjectType objectType) {
        ObjectType res = objectTypeService.update(objectType);
        return new ResponseEntity<Object>(res, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> getAll() throws NoSuchMethodException {
        return new ResponseEntity<Object>(objectTypeService.getAllHierarchy(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") long id) throws NoSuchMethodException {
        objectTypeService.delete(id);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
}
