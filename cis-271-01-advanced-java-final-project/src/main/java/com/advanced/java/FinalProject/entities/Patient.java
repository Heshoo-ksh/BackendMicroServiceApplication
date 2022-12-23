package com.advanced.java.FinalProject.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "patient")
@Setter
@Getter
@NoArgsConstructor
public class Patient {

    @OneToMany (mappedBy = "patient",cascade = CascadeType.ALL)//orphanRemoval = true
    private Set<Room> rooms = new HashSet<>();
    @OneToMany (mappedBy = "patient",cascade = CascadeType.ALL)
    private Set<Dietitian> dietitians = new HashSet<>();

    @Id @NonNull
    @GeneratedValue(strategy = GenerationType.AUTO) private Long id;
    private String firstName;
    private String lastName;
    private String middleName;
    private String ssn;
    private Long age;
    private Long height;
    private Long weight;
    private String dateOfBirth;
    private String symptoms;
    private String diagnosis;

    public Patient (String firstName,String lastName,String middleName,String ssn, Long age,Long height,Long weight,String dateOfBirth,String symptoms,String diagnosis)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName =   middleName;
        this.ssn = ssn;
        this.age = age;
        this.height = height;
        this.weight = weight;
        this.dateOfBirth = dateOfBirth;
        this.symptoms = symptoms;
        this.diagnosis = diagnosis;
    }
    @Override
    public String toString()
    {
        return "[ FirstName: " + this.firstName + " ,middle initial: " +this.middleName + " ,lastName: " +  this.lastName + " ]";
    }
}
