package com.medical.solution.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class GeneralController {

    @GetMapping("/test")
    public ResponseEntity<?> getTestData() {
        Map<String, String> response = new HashMap<>();
        response.put("1", "One");
        response.put("2", "Two");
        response.put("3", "Three");
        return new ResponseEntity<Object>(response, HttpStatus.OK);
    }
}
