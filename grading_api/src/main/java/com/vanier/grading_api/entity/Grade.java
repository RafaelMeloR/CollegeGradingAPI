package com.vanier.grading_api.entity;

import java.util.Date;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@ToString
public class Grade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ToString.Exclude
    private Long id;

    private Long idProfessor;
    private Long idStudent;
    private Long idAssignature;
    private String category;
    private Double assesmentWeight;
    private Double gradeValue;
    private Date dateSubmission;
}