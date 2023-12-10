package com.vanier.grading_api.controller;

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
        Assignature SavedAssignature = assignatureService.save(assignature);
        return SavedAssignature != null
                ? new ResponseEntity<>(SavedAssignature, HttpStatus.CREATED)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    // Find assignature by id
    @GetMapping("/findById")
    public ResponseEntity<Assignature> findById(@RequestParam Long id) {
        Optional<Assignature> assignature = assignatureService.findById(id);
        return assignature.map(
                value -> new ResponseEntity<>(value, HttpStatus.ACCEPTED))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));

    }

    // Update assignature by id
    @PutMapping("/update")
    public ResponseEntity<Assignature> update(@RequestBody Assignature assignature) {

        // to check if assignature exists and if not return 404 also check if is
        // working :D
        assignatureService.update(assignature.getId());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // Delete assignature by id
    @DeleteMapping("/delete")
    public ResponseEntity<Assignature> delete(@RequestParam Long id) {
        assignatureService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
