package com.vanier.grading_api.entity;

import java.sql.Date;

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
    private String firstName;
    private String lastName;
    private String email;
    private Date dateOfBirth;
    private String phoneNumber;
    private String role;

    //@OneToMany(cascade=CascadeType.ALL)
    // import from new lib
    //private List<CustomerAddress> addresses;
}
