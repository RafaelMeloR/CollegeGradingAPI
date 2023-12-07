package com.vanier.grading_api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vanier.grading_api.entity.Assignature;
import com.vanier.grading_api.service.AssignatureService;

@RestController
@RequestMapping("/assignature")
public class AssignatureController {

    @Autowired
    private AssignatureService assignatureService;

    @PostMapping("/save")
    public ResponseEntity<Assignature> save() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'save'");
    }

    @GetMapping("/findById")
    public ResponseEntity<Assignature> findById() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findById'");
    }

    @PutMapping("/update")
    public ResponseEntity<Assignature> update() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @DeleteMapping("/delete")
    public void delete() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }
}
