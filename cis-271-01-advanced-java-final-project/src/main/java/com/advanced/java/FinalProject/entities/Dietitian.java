package com.advanced.java.FinalProject.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "dietitian")
@Setter
@Getter
@NoArgsConstructor
public class Dietitian {

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @Id @NonNull
    @GeneratedValue (strategy = GenerationType.AUTO) private Long id;

    private Long currentWeight;
    private Long weightDifferance;
    private String woundLocation;
    private boolean tubeFeeding;
    private String doctorName;


    public Dietitian(Long currentWeight,Long weightDifferance, String woundLocation, boolean tubeFeeding, String doctorName, Patient patient)
    {
        super();
        this.currentWeight = currentWeight;
        this.weightDifferance = weightDifferance;
        this.woundLocation = woundLocation;
        this.doctorName = doctorName;
        this.tubeFeeding = tubeFeeding;
        this.patient = patient;
    }

    @Override
    public String toString()
    {
        return "[ CurrentWeight:" + this.currentWeight + " ,weightDifferance: " + this.weightDifferance + " ,woundLocation: " + this.woundLocation + " ,doctorName: " + this.doctorName + " ]";
    }
}
