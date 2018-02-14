package com.medical.solution.controller;

import com.medical.solution.entity.AttrGroup;
import com.medical.solution.service.i.AttrGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<?> add(@RequestBody AttrGroup attrGroup) {
        AttrGroup res = attrGroupService.add(attrGroup);
        return new ResponseEntity<Object>(res, HttpStatus.CREATED);
    }

    @PostMapping
    public ResponseEntity<?> update(@RequestBody AttrGroup attrGroup) {
        AttrGroup res = attrGroupService.update(attrGroup);
        return new ResponseEntity<Object>(res, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> getAll() {
        return new ResponseEntity<Object>(attrGroupService.getAll(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") long id) throws NoSuchMethodException {
        attrGroupService.delete(id);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @GetMapping(value = "/by-name/{name}")
    public ResponseEntity<?> getAllByName(@PathVariable("name") String name) {
        List<AttrGroup> attrGroups = attrGroupService.findAllByName(name);
        return new ResponseEntity<Object>(attrGroups, HttpStatus.OK);
    }
}
