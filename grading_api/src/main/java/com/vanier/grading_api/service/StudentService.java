package com.vanier.grading_api.service;

import java.util.Optional;

import com.vanier.grading_api.entity.Student;

public interface StudentService {
    
    Student save(Student student);
    
    Optional<Student> findById(Long id);

    void update(Long id);

    void delete(Long id);
}
