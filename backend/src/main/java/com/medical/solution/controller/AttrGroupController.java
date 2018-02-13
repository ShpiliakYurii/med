package com.medical.solution.controller;

import com.medical.solution.entity.MAttrGroup;
import com.medical.solution.service.i.AttrGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/attr-group")
@CrossOrigin(origins = "http://localhost:4200")
public class AttrGroupController {
    private AttrGroupService attrGroupService;

    @Autowired
    public AttrGroupController(AttrGroupService attrGroupService) {
        this.attrGroupService = attrGroupService;
    }

    @PutMapping
    public ResponseEntity<?> add(@RequestBody MAttrGroup attrGroup) {
        MAttrGroup res = attrGroupService.add(attrGroup);
        return new ResponseEntity<Object>(res, HttpStatus.CREATED);
    }

    @PostMapping
    public ResponseEntity<?> update(@RequestBody MAttrGroup attrGroup) {
        MAttrGroup res = attrGroupService.update(attrGroup);
        return new ResponseEntity<Object>(res, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> getAll() {
        return new ResponseEntity<Object>(attrGroupService.getAll(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") long id) {
        attrGroupService.delete(id);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
}
