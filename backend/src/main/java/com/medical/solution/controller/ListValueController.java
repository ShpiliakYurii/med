package com.medical.solution.controller;

import com.medical.solution.entity.ListValue;
import com.medical.solution.service.i.ListValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/list-value")
@CrossOrigin(origins = "http://localhost:4200")
public class ListValueController {

    private ListValueService listValueService;

    @Autowired
    public ListValueController(ListValueService listValueService) {
        this.listValueService = listValueService;
    }

    @PutMapping
    public ResponseEntity<?> add(@RequestBody ListValue listValue) {
        ListValue newLv = this.listValueService.add(listValue);
        return new ResponseEntity<Object>(newLv, HttpStatus.OK);
    }
}
