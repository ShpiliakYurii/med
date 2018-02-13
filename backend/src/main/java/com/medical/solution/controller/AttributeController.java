package com.medical.solution.controller;

import com.medical.solution.entity.MAttribute;
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

    @Autowired
    public AttributeController(AttributeService attributeService) {
        this.attributeService = attributeService;
    }

    @PutMapping
    public ResponseEntity<?> add(@RequestBody MAttribute attr) {
        MAttribute res = attributeService.add(attr);
        return new ResponseEntity<Object>(res, HttpStatus.CREATED);
    }

    @PostMapping
    public ResponseEntity<?> update(@RequestBody MAttribute attr) {
        MAttribute res = attributeService.update(attr);
        return new ResponseEntity<Object>(res, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> getAll() {
        return new ResponseEntity<Object>(attributeService.getAll(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") long id) {
        attributeService.delete(id);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
}
