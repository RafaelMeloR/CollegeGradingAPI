package com.vanier.grading_api.service;

import java.util.Optional;

import com.vanier.grading_api.entity.Assignature;

public interface AssignatureService {
    Assignature save(Assignature assignature);

    Optional<Assignature> findById(Long id);

    void update(Long id);

    void delete(Long id);

}
