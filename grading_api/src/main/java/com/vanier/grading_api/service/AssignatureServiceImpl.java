package com.vanier.grading_api.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vanier.grading_api.entity.Assignature;
import com.vanier.grading_api.repository.AssignatureRepository;

@Service
public class AssignatureServiceImpl implements AssignatureService {

    @Autowired
    private AssignatureRepository assignatureRepository;

    // Save assignature
    @Override
    public Assignature save(Assignature assignature) {
        return assignatureRepository.save(assignature);
    }

    // Find assignature by id
    @Override
    public Optional<Assignature> findById(Long id) {
        return assignatureRepository.findById(id);
    }

    // Update assignature by id
    @Override
    public void update(Long id) {
        Optional<Assignature> assignature = assignatureRepository.findById(id);
        assignature.ifPresent(assignatureToUpdate -> assignatureRepository.save(assignatureToUpdate));
    }

    // Delete assignature by id
    @Override
    public void delete(Long id) {
        Optional<Assignature> assignature = assignatureRepository.findById(id);
        assignature.ifPresent(assignatureToDelete -> assignatureRepository.delete(assignatureToDelete));
    }


}
