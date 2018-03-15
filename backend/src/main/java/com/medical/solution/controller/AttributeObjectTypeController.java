package com.medical.solution.controller;

import com.medical.solution.entity.AttributeObjectType;
import com.medical.solution.service.i.AttributeObjectTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/attr-object-type")
@CrossOrigin(origins = "http://localhost:4200")
public class AttributeObjectTypeController {

    private AttributeObjectTypeService attributeObjectTypeService;

    @Autowired
    public AttributeObjectTypeController(AttributeObjectTypeService attributeObjectTypeService) {
        this.attributeObjectTypeService = attributeObjectTypeService;
    }

    @PutMapping
    public ResponseEntity<?> add(@RequestBody AttributeObjectType aot) {
        AttributeObjectType res = attributeObjectTypeService.add(aot);
        return new ResponseEntity<Object>(res, HttpStatus.CREATED);
    }

    @PostMapping
    public ResponseEntity<?> update(@RequestBody AttributeObjectType aot) {
        AttributeObjectType res = attributeObjectTypeService.update(aot);
        return new ResponseEntity<Object>(res, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> getAll() {
        return new ResponseEntity<Object>(attributeObjectTypeService.getAll(), HttpStatus.OK);
    }

    @DeleteMapping("/{attrId}/{objectTypeId}")
    public ResponseEntity<?> delete(@PathVariable("attrId") long id, @PathVariable("objectTypeId") long objectTypeId) {
        attributeObjectTypeService.delete(id, objectTypeId);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
}
