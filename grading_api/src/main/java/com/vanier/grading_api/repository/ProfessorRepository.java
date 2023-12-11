package com.vanier.grading_api.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.vanier.grading_api.entity.Professor;

@Repository
public interface ProfessorRepository extends CrudRepository<Professor, Long> {

}
