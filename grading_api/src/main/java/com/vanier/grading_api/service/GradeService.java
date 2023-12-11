package com.vanier.grading_api.service;

import java.util.Optional;

import com.vanier.grading_api.entity.Grade;

public interface GradeService {

    Grade save(Grade grade);

    Optional<Grade> findById(Long id);

    void update(Long id);

    void delete(Long id);
}
