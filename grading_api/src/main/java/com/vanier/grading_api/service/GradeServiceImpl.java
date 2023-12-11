package com.vanier.grading_api.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vanier.grading_api.entity.Grade;
import com.vanier.grading_api.repository.GradeRepository;

@Service
public class GradeServiceImpl implements GradeService {

    @Autowired
    private GradeRepository gradeRepository;

    // Save grade
    @Override
    public Grade save(Grade grade) {
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
