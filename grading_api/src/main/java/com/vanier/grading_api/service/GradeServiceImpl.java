package com.vanier.grading_api.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vanier.grading_api.entity.Grade;
import com.vanier.grading_api.entity.Student;
import com.vanier.grading_api.repository.GradeRepository;
import com.vanier.grading_api.repository.StudentRepository;

@Service
public class GradeServiceImpl implements GradeService {

    @Autowired
    private GradeRepository gradeRepository;
    @Autowired
    private StudentRepository studentRepository;
    // Save grade
    @Override
    public Grade save(Grade grade) {
        // Check if grade object has student defined
        Student student = studentRepository.findById(grade.getIdStudent()).get();
        List<Grade> grades = new ArrayList<>();
        grades = student.getGrades();
        grades.add(grade);
        student.setGrades(grades);
        // Save student
        studentRepository.save(student);
        // Save grade
        return gradeRepository.save(grade);

    }

    // Find grade by id
    @Override
    public Optional<Grade> findById(Long id) {
        return gradeRepository.findById(id);
    }

    // Update grade by id
    @Override
    public void update(Long id) {
        Optional<Grade> grade = gradeRepository.findById(id);
        grade.ifPresent(gradeToUpdate -> gradeRepository.save(gradeToUpdate));
    }

    // Delete grade by id
    @Override
    public void delete(Long id) {
        Optional<Grade> grade = gradeRepository.findById(id);
        grade.ifPresent(gradeToDelete -> gradeRepository.delete(gradeToDelete));
    }
    
}
