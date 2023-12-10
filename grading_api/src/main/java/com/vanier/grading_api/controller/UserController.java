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
import com.vanier.grading_api.entity.Professor;
import com.vanier.grading_api.service.ProfessorService;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private ProfessorService professorService;

    /* ==================PROFESSOR======================== */

    @PostMapping("/professor/save")
    public ResponseEntity<Professor> save(@RequestBody Professor professor) {

        // TO FINISH
        Professor savedProfessor = professorService.save(professor);
        return savedProfessor != null
                ? new ResponseEntity<>(savedProfessor, HttpStatus.CREATED)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);

    }

    @GetMapping("/professor/findById")
    public ResponseEntity<Professor> findById(@RequestParam Long id) {
        Optional<Professor> professor = professorService.findById(id);
        return professor.map(
                value -> new ResponseEntity<>(value, HttpStatus.ACCEPTED))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/professor/update")
    public ResponseEntity<Professor> update(@RequestBody Professor professor) {
        // to check if professor exists and if not return 404 also check if it works :D
        professorService.update(professor.getId());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/professor/delete")
    public ResponseEntity<Professor> delete(@RequestParam Long id) {
        professorService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);

    }

    /* ==================STUDENT======================== */
}
