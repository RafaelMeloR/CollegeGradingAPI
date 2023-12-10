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

    @Override
    public Assignature save(Assignature assignature) {
        return assignatureRepository.save(assignature);
    }

    @Override
    public Optional<Assignature> findById(Long id) {
        return assignatureRepository.findById(id);
    }

    @Override
    public void update(Long id) {
        Optional<Assignature> assigToUpdate = assignatureRepository.findById(id);
        assigToUpdate.ifPresent(value -> assignatureRepository.save(value)); 
    }

    @Override
    public void delete(Long id) {
        Optional<Assignature> assigToDelete = assignatureRepository.findById(id);
        assigToDelete.ifPresent(value -> assignatureRepository.delete(value)); 
    }


}
