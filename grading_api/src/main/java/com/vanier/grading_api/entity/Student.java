package com.vanier.grading_api.entity;

import java.util.Date;
import java.util.List;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@ToString
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ToString.Exclude
    private Long id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private String email;
    private Date dateOfBirth;
    private String phoneNumber;

    @OneToMany(cascade=CascadeType.ALL)
    private List<Grade> grades;

    @OneToMany(cascade=CascadeType.ALL)
    private List<Assignature> assignatures;

}
