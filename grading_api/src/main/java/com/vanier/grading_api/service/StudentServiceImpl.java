package com.vanier.grading_api.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vanier.grading_api.entity.Student;
import com.vanier.grading_api.repository.StudentRepository;

@Service
public class StudentServiceImpl implements StudentService{

    @Autowired
    private StudentRepository repo;

    @Autowired
    private AssignatureService assignatureService;

    //@Autowired GradeService gradeService;

    @Override
    public Student save(Student student) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'save'");
    }

    @Override
    public Optional<Student> findById(Long id) {
        return repo.findById(id);
    }

    @Override
    public void update(Long id) {
        Optional<Student> student = repo.findById(id);
        student.ifPresent( studentToUpdate -> repo.save(studentToUpdate));
    }

    @Override
    public void delete(Long id) {
       Optional<Student> student = repo.findById(id);
        student.ifPresent( studentToDelete -> repo.delete(studentToDelete));
    }
    
}
