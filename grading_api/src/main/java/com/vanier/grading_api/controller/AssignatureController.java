package com.vanier.grading_api.controller;

import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
import com.vanier.grading_api.service.AssignatureService;

@RestController
@RequestMapping("/assignature")
public class AssignatureController {

    @Autowired
    private AssignatureService assignatureService;

    // Save assignature
    @PostMapping("/save")
    public ResponseEntity<Assignature> save(@RequestBody Assignature assignature) {

        Assignature savedAssignature = assignatureService.save(assignature);
        return savedAssignature != null 
            ? new ResponseEntity<>(savedAssignature, HttpStatus.CREATED)
            : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);

    }

    // Find assignature by id
    @GetMapping("/findById")
    public ResponseEntity<Assignature> findById(@RequestParam Long id) {
        Optional<Assignature> assignature = assignatureService.findById(id);
        return assignature.map( 
            //Response if data is found
            value -> new ResponseEntity<>(value, HttpStatus.FOUND))
            //Response when unsuccesful
            .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Update assignature by id
    @PutMapping("/update")
    public ResponseEntity<Assignature> update(@RequestBody Assignature assignature) {
        Optional<Assignature> existingAssignatureFound = assignatureService.findById(assignature.getId());
        if (existingAssignatureFound.isPresent()) {
                Assignature updatedAssignature = assignatureService.save(assignature);
                return new ResponseEntity<>(updatedAssignature, HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
        }
    }

    // Delete assignature by id
    @DeleteMapping("/delete")
    public ResponseEntity<Assignature> delete(@RequestParam Long id) {
        Optional<Assignature> deletedAssignature = assignatureService.findById(id);
        if(deletedAssignature.isPresent()){
            assignatureService.delete(id);
            return new ResponseEntity<>(deletedAssignature.get(), HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);

    }
}
