package com.vanier.grading_api.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.vanier.grading_api.entity.Grade;

@Repository
public interface GradeRepository extends CrudRepository<Grade,Long>{}
