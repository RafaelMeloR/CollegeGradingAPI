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
import com.vanier.grading_api.entity.Student;
import com.vanier.grading_api.service.ProfessorService;
import com.vanier.grading_api.service.StudentService;

@RestController
@RequestMapping("/")
public class UserController {

    @Autowired
    private ProfessorService professorService;

    /* ==================PROFESSOR======================== */

    @PostMapping("/professor/save")
    public ResponseEntity<Professor> saveProfessor(@RequestBody Professor professor) {

        Professor savedProfessor = professorService.save(professor);
        return savedProfessor != null
                ? new ResponseEntity<>(savedProfessor, HttpStatus.CREATED)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);

    }

    @GetMapping("/professor/findById")
    public ResponseEntity<Professor> findByIdProfessor(@RequestParam Long id) {
        Optional<Professor> professor = professorService.findById(id);
        return professor.map(
                value -> new ResponseEntity<>(value, HttpStatus.ACCEPTED))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/professor/update")
    public ResponseEntity<Professor> updateProfessor(@RequestBody Professor professor) {
        Optional<Professor> professorToUpdate = professorService.findById(professor.getId());
        if (professorToUpdate.isPresent()) {
            Professor updatedProfessor = professorService.save(professor);
            return new ResponseEntity<>(updatedProfessor, HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @DeleteMapping("/professor/delete")
    public ResponseEntity<Professor> deleteProfessor(@RequestParam Long id) {
        Optional<Professor> deletedProfessor = professorService.findById(id);
        if (deletedProfessor.isPresent()) {
            professorService.delete(id);
            return new ResponseEntity<>(deletedProfessor.get(), HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);

    }

    /* ==================STUDENT======================== */

    @Autowired
    private StudentService studentService;

    @PostMapping("/student/save")
    public ResponseEntity<Student> saveStudent(@RequestBody Student student) {
        Student savedStudent = studentService.save(student);
        return savedStudent != null
                ? new ResponseEntity<>(savedStudent, HttpStatus.CREATED)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);

    }

    @GetMapping("/student/findById")
    public ResponseEntity<Student> findByIdStudent(@RequestParam Long id) {
        Optional<Student> student = studentService.findById(id);
        return student.map(
                value -> new ResponseEntity<>(value, HttpStatus.ACCEPTED))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/student/update")
    public ResponseEntity<Student> updateStudent(@RequestBody Student student) {

        if (student.getId() == studentService.findById(student.getId()).get().getId()) {
            Student updatedStudent = studentService.save(student);
            return new ResponseEntity<>(updatedStudent, HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @DeleteMapping("/student/delete")
    public ResponseEntity<Student> deleteStudent(@RequestParam Long id) {
        Optional<Student> deletedStudent = studentService.findById(id);
        if (deletedStudent.isPresent()) {
            studentService.delete(id);
            return new ResponseEntity<>(deletedStudent.get(), HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);

    }

}
