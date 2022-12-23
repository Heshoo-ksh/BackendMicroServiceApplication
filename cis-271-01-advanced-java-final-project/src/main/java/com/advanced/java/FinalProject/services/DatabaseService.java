package com.advanced.java.FinalProject.services;

import com.advanced.java.FinalProject.entities.Dietitian;
import com.advanced.java.FinalProject.entities.Patient;
import com.advanced.java.FinalProject.entities.Room;
import com.advanced.java.FinalProject.repositories.DietitianRepository;
import com.advanced.java.FinalProject.repositories.PatientRepository;
import com.advanced.java.FinalProject.repositories.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DatabaseService {

    @Autowired private PatientRepository patientRepository;
    @Autowired private RoomRepository roomRepository;
    @Autowired private DietitianRepository dietitianRepository;

    //Patient Services
    public Patient savePatient(Patient patient){return patientRepository.save(patient);}
    //Room Services
    public Room saveRoom(Room room){return roomRepository.save(room);}
    //Dietitian Services
    public Dietitian saveDietitian( Dietitian dietitian) {return dietitianRepository.save(dietitian);}

    //get patient
    public Patient getPatientById(Long patientId){ return patientRepository.findById_(patientId);}
    public Optional<Patient> getPatientByIdOptional(Long patientId){ return patientRepository.findById(patientId);}

    public Dietitian getDietitianByPatientId(Long id) { return dietitianRepository.findBypatient_id(id);}

    //delete
    public void deletePatientByPatientId(Long patientId) { patientRepository.deleteById(patientId);}
    public void deleteDietitianById(Long id) {dietitianRepository.deleteById(id);}

}
