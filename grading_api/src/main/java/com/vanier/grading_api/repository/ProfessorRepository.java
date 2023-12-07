package com.vanier.grading_api.repository;

import org.springframework.data.repository.CrudRepository;

import com.vanier.grading_api.entity.Professor;

public interface ProfessorRepository extends CrudRepository<Professor, Long> {

}
