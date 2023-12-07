package com.vanier.grading_api.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.vanier.grading_api.entity.Assignature;

@Repository
public interface AssignatureRepository extends CrudRepository<Assignature, Long> {

}
