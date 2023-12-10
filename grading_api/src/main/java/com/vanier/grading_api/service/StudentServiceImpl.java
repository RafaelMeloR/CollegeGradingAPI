package com.vanier.grading_api.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vanier.grading_api.entity.Student;
import com.vanier.grading_api.repository.AssignatureRepository;
import com.vanier.grading_api.repository.GradeRepository;
import com.vanier.grading_api.repository.StudentRepository;

@Service
public class StudentServiceImpl implements StudentService{

    @Autowired private StudentRepository studentRepository;
    @Autowired private AssignatureRepository assignatureRepository;
    @Autowired private GradeRepository gradeRepository;

    @Override
    public Student save(Student student) {

        //Check if student object has assignatures defined
        if(student.getAssignatures() != null){
            //Since we have a list, for each assignature perform repo save method
            student.getAssignatures().forEach( assignature -> assignatureRepository.save(assignature));         
        }
        //Check if student object has grades defined
        if(student.getGrades() != null){
            //Since we have a list, for each assignature perform repo save method
            student.getGrades().forEach( grade -> gradeRepository.save(grade));    
        }
        return studentRepository.save(student);
    }

    @Override
    public Optional<Student> findById(Long id) {
        return studentRepository.findById(id);
    }

    @Override
    public void update(Long id) {
        Optional<Student> student = studentRepository.findById(id);
        student.ifPresent( studentToUpdate -> studentRepository.save(studentToUpdate));
    }

    @Override
    public void delete(Long id) {
       Optional<Student> student = studentRepository.findById(id);
        student.ifPresent( studentToDelete -> studentRepository.delete(studentToDelete));
    }   
}
