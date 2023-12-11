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

import com.vanier.grading_api.entity.Grade;
import com.vanier.grading_api.service.GradeService;

@RestController
@RequestMapping("grade")
public class GradeController {

    @Autowired
    private GradeService gradeService;

    // Save grade
    @PostMapping("/save")
    public ResponseEntity<Grade> save(@RequestBody Grade grade) {
        Grade savedGrade = gradeService.save(grade);
        return savedGrade != null
                ? new ResponseEntity<>(savedGrade, HttpStatus.CREATED)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    // Find grade by id
    @GetMapping("/findById")
    public ResponseEntity<Grade> findById(@RequestParam Long id) {
        Optional<Grade> grade = gradeService.findById(id);
        return grade.map(
                value -> new ResponseEntity<>(value, HttpStatus.ACCEPTED))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Update grade by id
    @PutMapping("/update")
    public ResponseEntity<Grade> update(@RequestBody Grade grade) {
        if (grade.getId() == gradeService.findById(grade.getId()).get().getId()) {
            Grade updateGrade = gradeService.save(grade);
            return new ResponseEntity<>(updateGrade, HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    // Delete grade by id
    @DeleteMapping("/delete")
    public ResponseEntity<Grade> delete(@RequestParam Long id) {
        gradeService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    
}
