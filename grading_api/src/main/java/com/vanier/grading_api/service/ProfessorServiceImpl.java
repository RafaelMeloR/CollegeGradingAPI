package com.vanier.grading_api.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vanier.grading_api.entity.Professor;
import com.vanier.grading_api.repository.ProfessorRepository;

@Service
public class ProfessorServiceImpl implements ProfessorService {
    @Autowired
    private ProfessorRepository professorRepository;

    // Save professor
    @Override
    public Professor save(Professor professor) {
        // To finish
        return professorRepository.save(professor);
    }

    // Find professor by id
    @Override
    public Optional<Professor> findById(Long id) {
        return professorRepository.findById(id);
    }

    // Update professor by id
    @Override
    public void update(Long id) {
        Optional<Professor> professor = professorRepository.findById(id);
        professor.ifPresent(professorToUpdate -> professorRepository.save(professorToUpdate));
    }

    // Delete professor by id
    @Override
    public void delete(Long id) {
        Optional<Professor> professor = professorRepository.findById(id);
        professor.ifPresent(professorToDelete -> professorRepository.delete(professorToDelete));
    }

}
