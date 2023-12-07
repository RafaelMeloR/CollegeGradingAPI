package com.vanier.grading_api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
    public ResponseEntity<Professor> save() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'save'");
    }

    @GetMapping("/professor/findById")
    public ResponseEntity<Professor> findById() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'save'");
    }

    @PutMapping("/professor/update")
    public ResponseEntity<Professor> update() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'save'");
    }

    @DeleteMapping("/professor/delete")
    public void delete() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'save'");
    }

    /* ==================STUDENT======================== */
}
