package com.medical.solution.controller;

import com.medical.solution.entity.AttrTypeDef;
import com.medical.solution.service.i.AttrTypeDefService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/attr-type-def")
@CrossOrigin(origins = "http://localhost:4200")
public class AttrTypeDefController {

    private AttrTypeDefService attrTypeDefService;

    @Autowired
    public AttrTypeDefController(AttrTypeDefService attrTypeDefService) {
        this.attrTypeDefService = attrTypeDefService;
    }

    @PutMapping
    public ResponseEntity<?> add(@RequestBody AttrTypeDef atd) {
        AttrTypeDef res = attrTypeDefService.add(atd);
        return new ResponseEntity<Object>(res, HttpStatus.CREATED);
    }

    @GetMapping(value = "/by-name/{name}")
    public ResponseEntity<?> getAllByName(@PathVariable("name") String name) {
        List<AttrTypeDef> attrTypeDefs = attrTypeDefService.findAllByName(name);
        return new ResponseEntity<Object>(attrTypeDefs, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") long id) throws NoSuchMethodException {
        return new ResponseEntity<Object>(this.attrTypeDefService.getById(id), HttpStatus.OK);
    }
}
