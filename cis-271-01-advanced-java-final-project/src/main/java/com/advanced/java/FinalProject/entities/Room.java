package com.advanced.java.FinalProject.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

import javax.persistence.*;
import java.util.Optional;

@Entity
@Table(name = "room")
@Setter
@Getter
@NoArgsConstructor
public class Room {

    @JsonIgnore //do i rreally want this here?? or can i be efficient with solving the issue i face if i comment it out?
    @ManyToOne //(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @Id @NonNull
    @GeneratedValue (strategy = GenerationType.AUTO) private Long  id;
    private String roomType;
    private String roomNumber;

    public Room(String roomNumber, String roomType, Patient patient){
        //super();
        this.roomNumber = roomNumber;
        this.roomType = roomType;
        this.patient =  patient;
    }
    @Override
    public String toString()
    {
        return "[ roomNumber: " + this.roomNumber +" ,roomType: "+ this.roomType + " ]" ;
    }

}
