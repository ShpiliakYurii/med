package com.medical.solution.controller;

import com.medical.solution.entity.Attribute;
import com.medical.solution.entity.AttributeObjectType;
import com.medical.solution.service.i.AttributeObjectTypeService;
import com.medical.solution.service.i.AttributeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/attribute")
@CrossOrigin(origins = "http://localhost:4200")
public class AttributeController {
    private AttributeService attributeService;
    private AttributeObjectTypeService attributeObjectTypeService;

    @Autowired
    public AttributeController(AttributeService attributeService, AttributeObjectTypeService attributeObjectTypeService) {
        this.attributeService = attributeService;
        this.attributeObjectTypeService = attributeObjectTypeService;
    }

    @PutMapping
    public ResponseEntity<?> add(@RequestBody Attribute attr) {
        Attribute res = attributeService.add(attr);
        return new ResponseEntity<Object>(res, HttpStatus.CREATED);
    }

    @PutMapping("/{otId}/{options}")
    public ResponseEntity<?> add(@RequestBody Attribute attr, @PathVariable("otId") long otId,
                                 @PathVariable("options") int options) {
        Attribute res = attributeService.add(attr);
        this.attributeObjectTypeService.add(new AttributeObjectType(res.getAttrId(), otId, options));
        return new ResponseEntity<Object>(res, HttpStatus.CREATED);
    }

    @PostMapping
    public ResponseEntity<?> update(@RequestBody Attribute attr) {
        Attribute res = attributeService.update(attr);
        return new ResponseEntity<Object>(res, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> getAll() {
        return new ResponseEntity<Object>(attributeService.getAll(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") long id) throws NoSuchMethodException {
        attributeService.delete(id);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") long id) throws NoSuchMethodException {
        return new ResponseEntity<Object>(attributeService.getById(id), HttpStatus.OK);
    }
}
