package com.vanier.grading_api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vanier.grading_api.entity.Assignature;
import com.vanier.grading_api.entity.Grade;
import com.vanier.grading_api.service.GradeService;

@RestController
@RequestMapping("grade")
public class GradeController {

    @Autowired
    private GradeService service;

    @PostMapping("/save")
    public ResponseEntity<Assignature> save(@RequestBody Grade grade) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'save'");
    }

    @GetMapping("/")
    public ResponseEntity<Assignature> findById(@RequestParam Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findById'");
    }

    @PutMapping("/update")
    public ResponseEntity<Assignature> update() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @DeleteMapping("/")
    public void delete(@RequestParam Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }
    
}
