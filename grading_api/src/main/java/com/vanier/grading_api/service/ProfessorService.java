package com.vanier.grading_api.service;

import java.util.Optional;

import com.vanier.grading_api.entity.Professor;

public interface ProfessorService {

    Professor save(Professor professor);

    Optional<Professor> findById(Long id);

    void update(Long id);

    void delete(Long id);
}
